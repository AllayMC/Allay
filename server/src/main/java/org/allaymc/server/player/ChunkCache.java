package org.allaymc.server.player;

import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.jpountz.xxhash.XXHash64;
import net.jpountz.xxhash.XXHashFactory;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheMissResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Per-player blob cache for client-side chunk caching.
 * All methods are synchronized for thread-safety (async chunk sending + network thread).
 *
 * @author daoge_cmd
 */
public final class ChunkCache {

    private static final XXHash64 XXHASH64 = XXHashFactory.fastestJavaInstance().hash64();
    private static final int MAX_BLOBS = 4096;

    private final Long2ObjectOpenHashMap<byte[]> blobs = new Long2ObjectOpenHashMap<>();
    private final List<LongOpenHashSet> openTransactions = new ArrayList<>();
    private volatile int generation = 0;

    /**
     * Compute xxhash64 of blob data.
     */
    public static long computeHash(byte[] data) {
        return XXHASH64.hash(data, 0, data.length, 0);
    }

    /**
     * Get current generation (volatile read, no lock needed).
     */
    public int generation() {
        return generation;
    }

    /**
     * Atomically store blobs and open a transaction.
     * Returns null if generation mismatch, transaction cap reached, or insufficient capacity.
     * On success, stores all blobs, opens transaction, and returns hash array.
     */
    public synchronized long[] tryStoreBlobsAndOpenTransaction(int expectedGeneration, byte[]... blobData) {
        // Generation check - stale caller
        if (generation != expectedGeneration) {
            return null;
        }

        // Transaction cap check
        if (openTransactions.size() >= MAX_BLOBS) {
            return null;
        }

        // Compute hashes and count new unique blobs
        long[] hashes = new long[blobData.length];
        int newBlobCount = 0;
        for (int i = 0; i < blobData.length; i++) {
            hashes[i] = computeHash(blobData[i]);
            if (!blobs.containsKey(hashes[i])) {
                newBlobCount++;
            }
        }

        // Capacity check - only count NEW blobs
        if (blobs.size() + newBlobCount > MAX_BLOBS) {
            return null;
        }

        // Store all blobs
        for (int i = 0; i < blobData.length; i++) {
            blobs.putIfAbsent(hashes[i], blobData[i]);
        }

        // Open transaction
        var transaction = new LongOpenHashSet(hashes);
        openTransactions.add(transaction);

        return hashes;
    }

    /**
     * Handle blob status from client.
     * Resolve acks, build miss response for naks.
     * Returns null if no NAK blobs found in cache (stale NAKs after clear).
     */
    public synchronized ClientCacheMissResponsePacket handleBlobStatus(long[] acks, long[] naks) {
        // Resolve acks - remove from all open transactions
        for (long ack : acks) {
            openTransactions.removeIf(transaction -> {
                transaction.remove(ack);
                return transaction.isEmpty();
            });
        }

        // Build miss response for naks
        if (naks.length == 0) {
            return null;
        }

        var missResponse = new ClientCacheMissResponsePacket();
        var missBlobs = missResponse.getBlobs();
        boolean foundAny = false;

        for (long nak : naks) {
            byte[] blobData = blobs.get(nak);
            if (blobData != null) {
                missBlobs.put(nak, Unpooled.wrappedBuffer(blobData));
                foundAny = true;
            }
        }

        if (!foundAny) {
            return null;
        }

        return missResponse;
    }

    /**
     * Clear all blobs and transactions, increment generation.
     */
    public synchronized void clear() {
        blobs.clear();
        openTransactions.clear();
        generation++;
    }
}
