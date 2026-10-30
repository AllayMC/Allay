package org.allaymc.server.world.manager;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongComparator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lombok.Getter;
import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.Player;
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
 * {@code ChunkLoaderHolder} tracks
 * a loader's desired circular chunk region, requested send queue, and
 * chunks already admitted for that loader. Crossing a chunk boundary rebuilds
 * the desired region and distance-ordered request queue, while each tick admits
 * a bounded batch of available chunks.
 *
 * <p>
 * A missing chunk at the queue head delays farther requests, preserving the
 * existing center-first admission policy. Actual players receive chunk data
 * synchronously or through an optional sender thread; other loaders still
 * acquire residency membership without network delivery. The sent set records
 * admission rather than confirmed client receipt, and asynchronous batching
 * does not preserve the synchronous distance sort. This holder is not
 * thread-safe, and radius changes alone do not trigger a region rebuild until
 * movement changes the loader's chunk position.
 *
 * @author daoge_cmd
 */
@NotThreadSafe
public final class ChunkLoaderHolder {

    /**
     * Chunk manager owning this loader holder.
     */
    private final ChunkManager chunkManager;
    /**
     * {@code chunkLoader} retains the loader whose location and radius are
     * queried during ticks. External changes must respect manager coordination.
     */
    @Getter
    private final ChunkLoader chunkLoader;
    /**
     * {@code player} holds the actual player controller captured at
     * construction, or {@code null} for loaders without player-specific
     * delivery.
     */
    private final Player player;
    /**
     * {@code chunkDistanceComparatorHashed} compares hashes using the loader's
     * current position. The loader location must remain stable during sorting.
     */
    private final LongComparator chunkDistanceComparatorHashed;
    /**
     * {@code chunkDistanceComparator} compares ready chunk references using the
     * loader's current position. Callers keep that location stable during sorting.
     */
    private final Comparator<Chunk> chunkDistanceComparator;
    /**
     * {@code sentChunks} stores hashes admitted for residency and delivery.
     * Membership can precede asynchronous sender execution or actual client
     * receipt.
     */
    private final LongOpenHashSet sentChunks;
    /**
     * {@code inRadiusChunks} holds the desired circular region from the last
     * chunk-position rebuild. Radius changes alone leave this set unchanged.
     */
    private final LongOpenHashSet inRadiusChunks;
    /**
     * {@code chunkSendingQueue} stores pending hashes in their rebuild-time
     * distance order. An unavailable head prevents admission of later entries.
     */
    private final LongArrayFIFOQueue chunkSendingQueue;
    /**
     * {@code asyncChunkSender} holds the sender created for actual players when
     * {@code ASYNC} is configured at construction. Later strategy changes do not
     * create it.
     */
    private AsyncChunkSender asyncChunkSender;
    /**
     * {@code lastLoaderChunkPosHashed} stores the last observed loader chunk
     * hash. Its initial {@code Long.MAX_VALUE} sentinel forces normal starting
     * locations through the first region rebuild.
     */
    private long lastLoaderChunkPosHashed;

    /**
     * Creates empty residency and sending state for the retained loader and
     * manager. An actual player receives a background sender only when {@code ASYNC} is
     * configured at construction. The initial location sentinel causes the
     * first ordinary tick to rebuild demand; the strategy must remain
     * compatible with the created sender. No chunks are requested until ticking
     * begins, and the loader must supply a radius suitable for queue sizing and
     * traversal.
     */
    ChunkLoaderHolder(ChunkManager chunkManager, ChunkLoader chunkLoader) {
        this.chunkManager = chunkManager;
        this.chunkLoader = chunkLoader;
        this.player = chunkLoader instanceof EntityPlayer p && p.isActualPlayer() ? p.getController() : null;
        this.chunkDistanceComparatorHashed = new HashedChunkDistanceComparator();
        this.chunkDistanceComparator = new ChunkDistanceComparator();
        this.sentChunks = new LongOpenHashSet();
        this.inRadiusChunks = new LongOpenHashSet();
        this.chunkSendingQueue = new LongArrayFIFOQueue((chunkLoader.getChunkLoadingRadius() * 2 + 1) * (chunkLoader.getChunkLoadingRadius() * 2 + 1));
        if (isActualPlayer() && AllayServer.getSettings().worldSettings().chunkSendingStrategy() == ASYNC) {
            this.asyncChunkSender = new AsyncChunkSender();
        }
        this.lastLoaderChunkPosHashed = Long.MAX_VALUE;
    }

    /**
     * Detaches this loader from admitted chunks and removes their player views
     * before stopping any sender. The sender is interrupted without joining, so
     * an in-flight delivery may finish during cleanup and no completion signal
     * is returned. Each cleanup phase starts only after the preceding one
     * returns normally; a residency or player-view failure can therefore leave
     * the sender running. The holder retains its tracking sets after removal,
     * so this operation does not make the same holder ready for registration
     * again.
     */
    public void onRemoved() {
        // Detach residency before removing player views and stopping background
        // delivery; these steps are not atomic, and an already running send can
        // overlap cleanup.
        removeChunkLoaderInChunks(sentChunks);
        if (isActualPlayer()) {
            sentChunks.forEach(player::removeChunk);
        }
        if (asyncChunkSender != null) {
            asyncChunkSender.stop();
        }
    }

    /**
     * Rebuilds desired chunks when the loader's floored chunk position changes,
     * then admits queued chunks. The rebuild updates the circular region,
     * detaches entries outside it, and replaces pending requests in distance
     * order. A radius-only change does not enter that path because movement is
     * the sole rebuild key. This method must run in the manager's coordinated
     * context; the asynchronous sender consumes a separate queue after
     * admission.
     */
    public void tick() {
        long currentLoaderChunkPosHashed;
        var floor = MathUtils.floor(chunkLoader.getLocation());
        // The movement hash is the only invalidation key, so staying within one
        // chunk preserves pending state even if the loader's radius changes.
        if ((currentLoaderChunkPosHashed = HashUtils.hashXZ(floor.x >> 4, floor.z >> 4)) != lastLoaderChunkPosHashed) {
            lastLoaderChunkPosHashed = currentLoaderChunkPosHashed;
            updateInRadiusChunks(floor);
            removeOutOfRadiusChunks();
            updateChunkSendingQueue();
        }
        sendQueuedChunks();
    }

    /**
     * Replaces the desired region with chunk hashes inside the loader's current
     * circular radius. The supplied block position must already be floored, so
     * shifting preserves the containing chunk for negative coordinates. Integer
     * offsets are filtered by squared distance before the loader's chunk origin
     * is added. The resulting set describes demand only; loading requests and
     * residency attachment occur in later phases using differences against
     * admitted chunks.
     */
    private void updateInRadiusChunks(Vector3i currentPos) {
        inRadiusChunks.clear();
        // The input is already floored, so shifting preserves negative chunk
        // boundaries.
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

    /**
     * Detaches admitted chunks that no longer belong to the desired region. The
     * set difference is a live view over sent and desired sets, so residency
     * removal and player notifications occur before the sent set is mutated.
     * Missing chunks are skipped during detachment. No network acknowledgment
     * is awaited, and failures can leave only part of this removal sequence
     * applied to the holder's state.
     */
    @SuppressWarnings("DataFlowIssue")
    private void removeOutOfRadiusChunks() {
        var difference = Sets.difference(sentChunks, inRadiusChunks);
        removeChunkLoaderInChunks(difference);
        // The difference view depends on sentChunks; consume its player removals
        // before mutating that backing set, or entries would disappear from the view.
        if (isActualPlayer()) {
            difference.forEach(player::removeChunk);
        }
        sentChunks.removeAll(difference);
    }

    /**
     * Detaches this loader from currently accessible chunks identified by the
     * supplied hashes. Missing or unloading chunks are skipped rather than
     * loaded, so cleanup does not create new residency work.
     */
    private void removeChunkLoaderInChunks(Set<Long> chunkHashes) {
        chunkHashes.stream()
                .map(chunkManager::getChunk)
                // Normal lookup hides missing or unloading chunks; cleanup
                // skips those entries instead of starting loads solely to
                // remove a loader reference.
                .filter(Objects::nonNull)
                .forEach(chunk -> chunk.removeChunkLoader(chunkLoader));
    }

    /**
     * Replaces pending requests with desired chunk hashes not already admitted
     * for this loader. The difference is sorted using the loader's current
     * location, and each chunk receives a load request before its hash is
     * enqueued. Load futures are intentionally not awaited here; later ticks
     * poll availability in queue order. Rebuilding discards old pending order
     * but retains the sent set so already admitted chunks are not resent.
     */
    @SuppressWarnings("ALL")
    private void updateChunkSendingQueue() {
        chunkSendingQueue.clear();
        // Retaining admitted hashes across rebuilds prevents duplicate deliveries,
        // while replacing pending hashes drops demand that no longer belongs to the region.
        var difference = Sets.difference(inRadiusChunks, sentChunks);
        difference.stream().sorted(chunkDistanceComparatorHashed).forEachOrdered(chunkHash -> {
            // Load submission and queue admission are separate phases: later
            // ticks poll availability, so this loop does not block on storage
            // or generation futures.
            chunkManager.loadChunk(chunkHash);
            chunkSendingQueue.enqueue(chunkHash);
        });
    }

    /**
     * Admits available chunks from the distance-ordered queue until its attempt
     * limit or first unavailable head. An unavailable hash is restored to the
     * head so farther chunks cannot overtake that admission dependency. The
     * do-while loop still makes one attempt for a nonempty queue when the
     * configured limit is nonpositive.
     *
     * <p>
     * Each admitted chunk gains loader membership before player delivery.
     * Synchronous delivery sorts the ready batch again, while asynchronous
     * delivery enqueues map values without that sort; both paths add hashes to
     * the sent set before client receipt is known. A sender must already exist
     * when {@code ASYNC} is selected, and delivery failure does not roll back all
     * preceding residency changes.
     */
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
                // Restore the unavailable head before ending this attempt batch, so
                // farther queued chunks cannot overtake its center-first admission order.
                chunkSendingQueue.enqueueFirst(chunkHash);
                break;
            }

            chunk.addChunkLoader(chunkLoader);
            chunkReadyToSend.put(chunkHash, chunk);
        } while (!chunkSendingQueue.isEmpty() && sentChunkCount < chunkLoader.getChunkMaxSendCountPerTick());

        if (!chunkReadyToSend.isEmpty()) {
            if (isActualPlayer()) {
                // Non-player loaders still retain chunk residency; only actual players
                // enter this controller-specific delivery path after attachment.
                var chunkSendingStrategy = AllayServer.getSettings().worldSettings().chunkSendingStrategy();
                if (chunkSendingStrategy == ASYNC) {
                    // Asynchronous submission records shared chunk references
                    // before delivery completes; this map-value iteration does
                    // not retain the distance order used by synchronous
                    // sending.
                    asyncChunkSender.addChunkToSendingQueue(chunkReadyToSend.values());
                } else {
                    // The ready map has no distance ordering, so synchronous delivery
                    // sorts again before invoking player callbacks for the admitted batch.
                    chunkReadyToSend.values()
                            .stream()
                            .sorted(chunkDistanceComparator)
                            .forEachOrdered(player::viewChunk);
                }
            }

            sentChunks.addAll(chunkReadyToSend.keySet());
        }
    }

    /**
     * Returns whether relative chunk offsets lie within the inclusive circular
     * radius. Squared integer arithmetic avoids square roots, but callers must
     * keep offsets and radius small enough to avoid overflow.
     */
    private boolean isChunkInRadius(int chunkX, int chunkZ, int radius) {
        return chunkX * chunkX + chunkZ * chunkZ <= radius * radius;
    }

    /**
     * Returns whether construction captured an actual player controller for
     * delivery.
     */
    private boolean isActualPlayer() {
        return this.player != null;
    }

    /**
     * {@code AsyncChunkSender} delivers admitted chunks from a blocking queue
     * on one virtual thread.
     * Shutdown discards queued work and interrupts the sender without waiting
     * for an in-flight callback.
     */
    private class AsyncChunkSender {

        /**
         * {@code chunkSendingQueue} retains shared chunks awaiting delivery.
         * {@code stop()} discards queued entries without undoing the holder's
         * {@code sentChunks} membership.
         */
        private final BlockingQueue<Chunk> chunkSendingQueue;
        /**
         * {@code isRunning} publishes whether the sender should take another
         * chunk. Clearing this flag does not cancel a callback already running.
         */
        private final AtomicBoolean isRunning;
        /**
         * {@code senderThread} performs virtual-thread delivery and receives
         * interruption on {@code stop()}. The owning holder receives neither a
         * join nor a completion future.
         */
        private final Thread senderThread;

        /**
         * Starts a virtual thread that takes queued chunks and delivers them
         * through the captured player controller. The running flag is checked
         * before each take, while interruption exits the thread without
         * retrying the interrupted operation. Other delivery exceptions are not
         * caught and can terminate the sender with queued work remaining.
         * Construction assumes an actual player exists; the enclosing holder
         * must call {@code stop()} when its registration ends.
         */
        public AsyncChunkSender() {
            this.chunkSendingQueue = new LinkedBlockingQueue<>();
            this.isRunning = new AtomicBoolean(true);
            // The blocking queue separates manager admission from player
            // delivery; only interruption is handled here, so other callback
            // failures terminate this sender without draining its remaining
            // work.
            this.senderThread = Thread.ofVirtual().start(() -> {
                while (isRunning.get()) {
                    try {
                        player.viewChunk(chunkSendingQueue.take());
                    } catch (InterruptedException e) {
                        // An interrupt is the sender's shutdown signal, so this
                        // catch exits rather than requeuing the removed item or
                        // waiting for another chunk.
                        return;
                    }
                }
            });
        }

        /**
         * Appends shared chunk references for later delivery by the sender
         * thread. Submission neither waits for delivery nor checks the running
         * flag, so callers must stop submitting when the holder is removed.
         */
        public void addChunkToSendingQueue(Collection<Chunk> chunks) {
            chunkSendingQueue.addAll(chunks);
        }

        /**
         * Requests termination by clearing the run flag, discarding queued
         * chunks, and interrupting the sender. No join occurs, so callers
         * cannot infer that a player callback already in progress has
         * completed. A sender blocked in queue removal exits through its
         * interruption handler, whereas a callback already running observes no
         * forced rollback. Submissions do not inspect {@code isRunning}, so the
         * owning holder must prevent new chunks from entering the queue once
         * cleanup begins.
         */
        public void stop() {
            // Clear the flag before discarding pending work and interrupting a
            // blocked take; this ordering prevents normal continuation but
            // cannot undo an already running delivery.
            isRunning.set(false);
            chunkSendingQueue.clear();
            senderThread.interrupt();
        }
    }

    /**
     * {@code HashedChunkDistanceComparator} orders pending chunk hashes by
     * squared distance from the loader's current chunk position. Each comparison
     * rereads that location, which must remain stable during sorting.
     */
    private final class HashedChunkDistanceComparator implements LongComparator {
        /**
         * Compares squared horizontal chunk distances from the loader's current
         * floored position. Equal distances compare as zero without a
         * coordinate tie-breaker; the caller must keep the loader location
         * stable during sorting and avoid integer-distance overflow.
         */
        @Override
        public int compare(long chunkHash1, long chunkHash2) {
            var floor = MathUtils.floor(chunkLoader.getLocation());
            // Flooring before shifting keeps negative positions in their containing chunk.
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

    /**
     * {@code ChunkDistanceComparator} orders ready chunk references by squared
     * distance from the loader's current chunk position. Each comparison rereads
     * that location, which must remain stable during sorting.
     */
    private final class ChunkDistanceComparator implements Comparator<Chunk> {
        /**
         * Compares squared horizontal chunk distances from the loader's current
         * floored position. Equal distances compare as zero without a
         * coordinate tie-breaker; the caller must keep the loader location
         * stable during sorting and avoid integer-distance overflow.
         */
        @Override
        public int compare(Chunk c1, Chunk c2) {
            var floor = MathUtils.floor(chunkLoader.getLocation());
            // Flooring before shifting keeps negative positions in their containing chunk.
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
