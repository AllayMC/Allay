package org.allaymc.server.world.manager;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongComparator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.manager.ChunkManager;
import org.allaymc.server.AllayServer;
import org.joml.Vector3i;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.server.ServerSettings.WorldSettings.ChunkSendingStrategy.ASYNC;

/**
 * ChunkLoaderHolder is a class that holds a chunk loader and manages the sending of chunks. It
 * will also load the chunk which should be sent to the chunk loader if the chunk is not loaded.
 *
 * @author daoge_cmd
 */
@NotThreadSafe
public final class ChunkLoaderHolder {

    /**
     * The chunk service that owns this chunk loader holder.
     */
    private final ChunkManager chunkManager;
    /**
     * The chunk loader that this holder is holding.
     */
    @Getter
    private final ChunkLoader chunkLoader;
    /**
     * A comparator that will decode chunk pos hash and compare chunk by its distance.
     */
    private final LongComparator chunkDistanceComparatorHashed;
    /**
     * A comparator that will compare chunk by its distance.
     */
    private final Comparator<Chunk> chunkDistanceComparator;
    /**
     * Stores all chunks that is in radius and have been sent.
     */
    private final LongOpenHashSet sentChunks;
    /**
     * Stores all chunks that is in radius.
     */
    private final LongOpenHashSet inRadiusChunks;
    /**
     * Stores all chunks that is waiting to be sent. The order is determined by their distance from the loader.
     */
    private final LongArrayFIFOQueue chunkSendingQueue;
    /**
     * The async chunk sender. Can be {@code null} if async chunk sending is not enabled.
     */
    private AsyncChunkSender asyncChunkSender;
    /**
     * A long value which is calculated based on the x and z coordinates of the loader's chunk pos using
     * method {@link HashUtils#hashXZ(int, int)}.
     */
    private long lastLoaderChunkPosHashed;

    ChunkLoaderHolder(ChunkManager chunkManager, ChunkLoader chunkLoader) {
        this.chunkManager = chunkManager;
        this.chunkLoader = chunkLoader;
        this.chunkDistanceComparatorHashed = new HashedChunkDistanceComparator();
        this.chunkDistanceComparator = new ChunkDistanceComparator();
        this.sentChunks = new LongOpenHashSet();
        this.inRadiusChunks = new LongOpenHashSet();
        this.chunkSendingQueue = new LongArrayFIFOQueue((chunkLoader.getChunkLoadingRadius() * 2 + 1) * (chunkLoader.getChunkLoadingRadius() * 2 + 1));
        if (AllayServer.getSettings().worldSettings().chunkSendingStrategy() == ASYNC) {
            this.asyncChunkSender = new AsyncChunkSender();
        }
        this.lastLoaderChunkPosHashed = Long.MAX_VALUE;
    }

    public void onRemoved() {
        removeChunkLoaderInChunks(sentChunks);
        sentChunks.forEach(chunkLoader::removeChunk);
        if (asyncChunkSender != null) {
            asyncChunkSender.stop();
        }
    }

    public void tick() {
        long currentLoaderChunkPosHashed;
        var floor = MathUtils.floor(chunkLoader.getLocation());
        if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(floor.x >> 4, floor.z >> 4)) != lastLoaderChunkPosHashed) {
            lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
            updateInRadiusChunks(floor);
            removeOutOfRadiusChunks();
            updateChunkSendingQueue();
        }
        sendQueuedChunks();
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

    @SuppressWarnings("DataFlowIssue")
    private void removeOutOfRadiusChunks() {
        var difference = Sets.difference(sentChunks, inRadiusChunks);
        removeChunkLoaderInChunks(difference);
        // Unload chunks out of range
        difference.forEach(chunkLoader::removeChunk);
        // The intersection of sentChunks and inRadiusChunks
        sentChunks.removeAll(difference);
    }

    private void removeChunkLoaderInChunks(Set<Long> chunkHashes) {
        chunkHashes.stream()
                .map(chunkManager::getChunk)
                .filter(Objects::nonNull)
                .forEach(chunk -> chunk.removeChunkLoader(chunkLoader));
    }

    @SuppressWarnings("ALL")
    private void updateChunkSendingQueue() {
        chunkSendingQueue.clear();
        // Chunks that have already been sent will not be resent
        var difference = Sets.difference(inRadiusChunks, sentChunks);
        difference.stream().sorted(chunkDistanceComparatorHashed).forEachOrdered(chunkHash -> {
            chunkManager.loadChunk(chunkHash);
            chunkSendingQueue.enqueue(chunkHash);
        });
    }

    private void sendQueuedChunks() {
        if (chunkSendingQueue.isEmpty()) {
            return;
        }

        var chunkReadyToSend = new Long2ObjectOpenHashMap<Chunk>();
        int sentChunkCount = 0;
        do {
            sentChunkCount++;
            var chunkHash = chunkSendingQueue.dequeueLong();
            var chunk = chunkManager.getChunk(chunkHash);
            if (chunk == null) {
                // Chunk is not loaded yet, so put it back at the head of the queue
                chunkSendingQueue.enqueueFirst(chunkHash);
                // Chunk must be sent from the middle to the outside, otherwise some chunks
                // will not be shown client-side. So break here
                break;
            }

            chunk.addChunkLoader(chunkLoader);
            chunkReadyToSend.put(chunkHash, chunk);
        } while (!chunkSendingQueue.isEmpty() && sentChunkCount < chunkLoader.getChunkMaxSendCountPerTick());

        if (!chunkReadyToSend.isEmpty()) {
            // TODO: send NCP here
            var chunkSendingStrategy = AllayServer.getSettings().worldSettings().chunkSendingStrategy();
            if (chunkSendingStrategy == ASYNC) {
                asyncChunkSender.addChunkToSendingQueue(chunkReadyToSend.values());
            } else {
                // Priority is given to sending chunks that are close to the chunk loader
                chunkReadyToSend.values()
                        .stream()
                        .sorted(chunkDistanceComparator)
                        .forEachOrdered(chunkLoader::viewChunk);
            }

            sentChunks.addAll(chunkReadyToSend.keySet());
        }
    }

    private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
        return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
    }

    private class AsyncChunkSender {

        private final BlockingQueue<Chunk> chunkSendingQueue;
        private final AtomicBoolean isRunning;

        public AsyncChunkSender() {
            this.chunkSendingQueue = new LinkedBlockingQueue<>();
            this.isRunning = new AtomicBoolean(true);
            Thread.ofVirtual().start(() -> {
                while (isRunning.get()) {
                    try {
                        chunkLoader.viewChunk(chunkSendingQueue.take());
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
