package org.allaymc.server.world.service;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongComparator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.joml.Vector3i;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.api.server.ServerSettings.WorldSettings.ChunkSendingStrategy.ASYNC;

/**
 * @author daoge_cmd
 */
public final class ChunkLoaderHolder {

    private final ChunkService chunkService;
    @Getter
    private final ChunkLoader chunkLoader;
    private final LongComparator chunkDistanceComparatorHashed;
    private final Comparator<Chunk> chunkDistanceComparator;
    // Save all chunk hash values that have been sent in the last tick
    private final LongOpenHashSet sentChunks;
    // Save all chunk hash values that will be sent in this tick
    private final LongOpenHashSet inRadiusChunks;
    private final int chunkTrySendCountPerTick;
    private final LongArrayFIFOQueue chunkSendingQueue;
    private AsyncChunkSender asyncChunkSender;
    private long lastLoaderChunkPosHashed;

    ChunkLoaderHolder(ChunkService chunkService, ChunkLoader chunkLoader) {
        this.chunkService = chunkService;
        this.chunkLoader = chunkLoader;
        this.chunkDistanceComparatorHashed = new HashedChunkDistanceComparator();
        this.chunkDistanceComparator = new ChunkDistanceComparator();
        this.sentChunks = new LongOpenHashSet();
        this.inRadiusChunks = new LongOpenHashSet();
        this.chunkTrySendCountPerTick = chunkLoader.getChunkTrySendCountPerTick();
        this.chunkSendingQueue = new LongArrayFIFOQueue((chunkLoader.getChunkLoadingRadius() * 2 + 1) * (chunkLoader.getChunkLoadingRadius() * 2 + 1));
        if (Server.SETTINGS.worldSettings().chunkSendingStrategy() == ASYNC) {
            this.asyncChunkSender = new AsyncChunkSender();
        }
        this.lastLoaderChunkPosHashed = Long.MAX_VALUE;
    }

    public void onRemoved() {
        removeChunkLoaderInChunks(sentChunks);
        chunkLoader.onChunkOutOfRange(sentChunks);
        if (asyncChunkSender != null) {
            asyncChunkSender.stop();
        }
    }

    public void tick() {
        if (!chunkLoader.isLoaderActive()) {
            return;
        }

        long currentLoaderChunkPosHashed;
        var floor = MathUtils.floor(chunkLoader.getLocation());
        if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(floor.x >> 4, floor.z >> 4)) != lastLoaderChunkPosHashed) {
            lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
            updateInRadiusChunks(floor);
            removeOutOfRadiusChunks();
            updateChunkSendingQueue();
        }
        loadAndSendQueuedChunks();
    }

    private void updateInRadiusChunks(Vector3i currentPos) {
        inRadiusChunks.clear();
        var loaderChunkX = currentPos.x >> 4;
        var loaderChunkZ = currentPos.z >> 4;
        var chunkLoadingRadius = chunkLoader.getChunkLoadingRadius();
        for (int rx = -chunkLoadingRadius; rx <= chunkLoadingRadius; rx++) {
            for (int rz = -chunkLoadingRadius; rz <= chunkLoadingRadius; rz++) {
                if (!isChunkInRadius(rx, rz, chunkLoadingRadius)) {
                    continue;
                }

                var chunkX = loaderChunkX + rx;
                var chunkZ = loaderChunkZ + rz;
                var hashXZ = HashUtils.hashXZ(chunkX, chunkZ);
                inRadiusChunks.add(hashXZ);
            }
        }
    }

    private void removeOutOfRadiusChunks() {
        var difference = Sets.difference(sentChunks, inRadiusChunks);
        removeChunkLoaderInChunks(difference);
        // Unload chunks out of range
        chunkLoader.onChunkOutOfRange(difference);
        // The intersection of sentChunks and inRadiusChunks
        sentChunks.removeAll(difference);
    }

    private void removeChunkLoaderInChunks(Set<Long> chunkHashes) {
        chunkHashes.stream()
                .map(chunkService::getChunk)
                .filter(Objects::nonNull)
                .forEach(chunk -> chunk.removeChunkLoader(chunkLoader));
    }

    @SuppressWarnings("ALL")
    private void updateChunkSendingQueue() {
        chunkSendingQueue.clear();
        // Chunks that have already been sent will not be resent
        var difference = Sets.difference(inRadiusChunks, sentChunks);
        difference.stream().sorted(chunkDistanceComparatorHashed).forEachOrdered(chunkSendingQueue::enqueue);
    }

    private void loadAndSendQueuedChunks() {
        if (chunkSendingQueue.isEmpty()) {
            return;
        }

        var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
        int triedSendChunkCount = 0;
        do {
            triedSendChunkCount++;
            var chunkHash = chunkSendingQueue.dequeueLong();
            var chunk = chunkService.getChunk(chunkHash);
            if (chunk == null) {
                if (chunkService.isChunkUnloaded(chunkHash)) {
                    chunkService.loadChunk(HashUtils.getXFromHashXZ(chunkHash), HashUtils.getZFromHashXZ(chunkHash));
                }
                chunkSendingQueue.enqueue(chunkHash);
                continue;
            }

            chunk.addChunkLoader(chunkLoader);
            chunkReadyToSend.put(chunkHash, chunk);
        } while (!chunkSendingQueue.isEmpty() && triedSendChunkCount < chunkTrySendCountPerTick);

        if (!chunkReadyToSend.isEmpty()) {
            chunkLoader.beforeSendChunks();
            var chunkSendingStrategy = Server.SETTINGS.worldSettings().chunkSendingStrategy();
            if (chunkSendingStrategy == ASYNC) {
                asyncChunkSender.addChunkToSendingQueue(chunkReadyToSend.values());
            } else {
                // Priority is given to sending chunks that are close to the chunk loader
                var lcpStream = chunkReadyToSend.values().stream();
                lcpStream.sorted(chunkDistanceComparator).forEachOrdered(chunk -> {
                    chunkLoader.sendPacket(createLevelChunkPacket(chunk));
                    chunkLoader.onChunkInRangeSend(chunk);
                });
            }

            sentChunks.addAll(chunkReadyToSend.keySet());
        }
    }

    private LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        var lcp = new LevelChunkPacket[1];
        chunk.applyOperation(unsafeChunk -> {
            lcp[0] = Server.SETTINGS.worldSettings().useSubChunkSendingSystem() ?
                    ((AllayUnsafeChunk) unsafeChunk).createSubChunkLevelChunkPacket() :
                    ((AllayUnsafeChunk) unsafeChunk).createFullLevelChunkPacketChunk();
        }, OperationType.READ, OperationType.READ);
        return lcp[0];
    }

    private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
        return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
    }

    private class AsyncChunkSender {

        private static final int DEFAULT_INITIAL_CAPACITY = 11;

        private final PriorityBlockingQueue<Chunk> chunkSendingQueue;
        private final AtomicBoolean isRunning;

        public AsyncChunkSender() {
            this.chunkSendingQueue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, chunkDistanceComparator);
            this.isRunning = new AtomicBoolean(true);
            Thread.ofVirtual().start(() -> {
                while (isRunning.get()) {
                    try {
                        // PriorityBlockingQueue ensure that every time we take the closest chunk from the queue
                        var chunk = chunkSendingQueue.take();
                        chunkLoader.sendPacket(createLevelChunkPacket(chunk));
                        chunkLoader.onChunkInRangeSend(chunk);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
        }

        public void addChunkToSendingQueue(Collection<Chunk> chunks) {
            chunkSendingQueue.addAll(chunks);
        }

        public void stop() {
            isRunning.set(false);
        }
    }

    private final class HashedChunkDistanceComparator implements LongComparator {
        @Override
        public int compare(long chunkHash1, long chunkHash2) {
            var floor = MathUtils.floor(chunkLoader.getLocation());
            var loaderChunkX = floor.x >> 4;
            var loaderChunkZ = floor.z >> 4;
            var chunkDX1 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash1);
            var chunkDZ1 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash1);
            var chunkDX2 = loaderChunkX - HashUtils.getXFromHashXZ(chunkHash2);
            var chunkDZ2 = loaderChunkZ - HashUtils.getZFromHashXZ(chunkHash2);
            return Integer.compare(
                    chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                    chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
            );
        }
    }

    private final class ChunkDistanceComparator implements Comparator<Chunk> {
        @Override
        public int compare(Chunk c1, Chunk c2) {
            var floor = MathUtils.floor(chunkLoader.getLocation());
            var loaderChunkX = floor.x >> 4;
            var loaderChunkZ = floor.z >> 4;
            var chunkDX1 = loaderChunkX - c1.getX();
            var chunkDZ1 = loaderChunkZ - c1.getZ();
            var chunkDX2 = loaderChunkX - c2.getX();
            var chunkDZ2 = loaderChunkZ - c2.getZ();
            return Integer.compare(
                    chunkDX1 * chunkDX1 + chunkDZ1 * chunkDZ1,
                    chunkDX2 * chunkDX2 + chunkDZ2 * chunkDZ2
            );
        }
    }
}
