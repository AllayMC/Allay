package org.allaymc.server.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.jpountz.xxhash.XXHash64;
import net.jpountz.xxhash.XXHashFactory;
import org.allaymc.server.AllayServer;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheMissResponsePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Global chunk blob cache with LRU eviction for client-side chunk caching.
 * <p>
 * This cache is shared across all players for memory efficiency and automatic blob deduplication.
 * When multiple players visit the same chunks, the blob data is stored only once in memory.
 * <p>
 * The cache uses Caffeine's LRU eviction policy to automatically remove least-recently-used blobs
 * when the cache reaches its maximum size (configured via {@code max-chunk-cache-blobs} in server settings).
 * <p>
 * Thread-safety: This class is thread-safe. The global blob cache is lock-free (Caffeine handles concurrency),
 * while per-player state is protected by synchronized blocks.
 *
 * @author daoge_cmd
 */
public final class ChunkCache {

    private static final XXHash64 XXHASH64 = XXHashFactory.fastestJavaInstance().hash64();
    private static ChunkCache instance;

    private final Cache<Long, byte[]> blobs;
    private final ConcurrentHashMap<UUID, PlayerCacheState> playerStates;

    private ChunkCache(int maxBlobs) {
        this.blobs = Caffeine.newBuilder()
                .maximumSize(maxBlobs)
                .build();
        this.playerStates = new ConcurrentHashMap<>();
    }

    /**
     * Initialize the global cache instance.
     * <p>
     * Must be called before any player uses the cache, typically during server startup.
     *
     * @param maxBlobs maximum number of blobs to cache globally
     * @throws IllegalStateException if the cache is already initialized
     */
    public static synchronized void initialize(int maxBlobs) {
        if (instance != null) {
            throw new IllegalStateException("ChunkCache already initialized");
        }
        instance = new ChunkCache(maxBlobs);
    }

    /**
     * Get the global cache instance.
     * <p>
     * If not yet initialized, performs fallback initialization using the configured max blob count
     * from server settings.
     *
     * @return the global cache instance
     */
    public static ChunkCache getInstance() {
        if (instance == null) {
            // Fallback initialization with default size
            initialize(AllayServer.getSettings().networkSettings().maxChunkCacheBlobs());
        }
        return instance;
    }

    /**
     * Compute xxhash64 hash of blob data.
     *
     * @param data the blob data to hash
     * @return the 64-bit hash value
     */
    public static long computeHash(byte[] data) {
        return XXHASH64.hash(data, 0, data.length, 0);
    }

    /**
     * Get or create player cache state.
     *
     * @param playerId the player's UUID
     * @return the player's cache state
     */
    private PlayerCacheState getPlayerState(UUID playerId) {
        return playerStates.computeIfAbsent(playerId, id -> new PlayerCacheState());
    }

    /**
     * Try to store blobs and open a transaction for a player.
     * <p>
     * This method atomically:
     * <ol>
     *   <li>Validates the generation matches (prevents stale callers after dimension change)</li>
     *   <li>Checks the per-player transaction limit (512 max)</li>
     *   <li>Computes hashes and stores all blobs in the global cache (deduplicated)</li>
     *   <li>Opens a transaction tracking these blobs for this player</li>
     * </ol>
     *
     * @param playerId the player's UUID
     * @param expectedGeneration the expected generation number (for staleness detection)
     * @param blobData the blob data to store
     * @return array of blob hashes if successful, {@code null} if generation mismatch or transaction cap reached
     */
    public long[] tryStoreBlobsAndOpenTransaction(UUID playerId, int expectedGeneration, byte[]... blobData) {
        PlayerCacheState state = getPlayerState(playerId);

        synchronized (state) {
            // Generation check - stale caller
            if (state.generation != expectedGeneration) {
                return null;
            }

            // Transaction cap check (per-player limit to prevent one player from hogging all transactions)
            if (state.openTransactions.size() >= 512) {
                return null;
            }

            // Compute hashes and store blobs in global cache
            long[] hashes = new long[blobData.length];
            for (int i = 0; i < blobData.length; i++) {
                hashes[i] = computeHash(blobData[i]);
                // Store in global cache (deduplicated automatically)
                blobs.put(hashes[i], blobData[i]);
            }

            // Open transaction for this player
            var transaction = new LongOpenHashSet(hashes);
            state.openTransactions.add(transaction);

            return hashes;
        }
    }

    /**
     * Handle blob status from client.
     * <p>
     * Processes acknowledgments (ACKs) by removing resolved blobs from the player's open transactions.
     * For negative acknowledgments (NAKs), builds a miss response packet containing the requested blob data.
     *
     * @param playerId the player's UUID
     * @param acks array of acknowledged blob hashes
     * @param naks array of missing blob hashes (client requests these)
     * @return miss response packet if any NAK blobs were found in cache, {@code null} otherwise
     */
    public ClientCacheMissResponsePacket handleBlobStatus(UUID playerId, long[] acks, long[] naks) {
        PlayerCacheState state = getPlayerState(playerId);

        synchronized (state) {
            // Resolve acks - remove from player's open transactions
            for (long ack : acks) {
                state.openTransactions.removeIf(transaction -> {
                    transaction.remove(ack);
                    return transaction.isEmpty();
                });
            }
        }

        // Build miss response for naks (read from global cache, no lock needed)
        if (naks.length == 0) {
            return null;
        }

        var missResponse = new ClientCacheMissResponsePacket();
        var missBlobs = missResponse.getBlobs();
        boolean foundAny = false;

        for (long nak : naks) {
            byte[] blobData = blobs.getIfPresent(nak);
            if (blobData != null) {
                missBlobs.put(nak, Unpooled.wrappedBuffer(blobData));
                foundAny = true;
            }
        }

        return foundAny ? missResponse : null;
    }

    /**
     * Clear player-specific state (transactions and generation).
     * <p>
     * Called when a player changes dimensions or worlds. Increments the generation counter
     * to invalidate any in-flight chunk encoding operations.
     * <p>
     * Note: This does NOT remove blobs from the global cache, as they may be shared with other players.
     *
     * @param playerId the player's UUID
     */
    public void clearPlayer(UUID playerId) {
        PlayerCacheState state = playerStates.get(playerId);
        if (state != null) {
            synchronized (state) {
                state.openTransactions.clear();
                state.generation++;
            }
        }
    }

    /**
     * Remove player state entirely.
     * <p>
     * Called when a player disconnects. Removes all per-player tracking data.
     * <p>
     * Note: This does NOT remove blobs from the global cache, as they may be shared with other players.
     *
     * @param playerId the player's UUID
     */
    public void removePlayer(UUID playerId) {
        playerStates.remove(playerId);
    }

    /**
     * Get current generation for a player.
     * <p>
     * The generation counter is incremented each time {@link #clearPlayer(UUID)} is called,
     * allowing detection of stale chunk encoding operations after dimension/world changes.
     *
     * @param playerId the player's UUID
     * @return the current generation number
     */
    public int getGeneration(UUID playerId) {
        return getPlayerState(playerId).generation;
    }

    /**
     * Get cache statistics.
     *
     * @return cache statistics including blob count, player count, and Caffeine stats
     */
    public CacheStats getStats() {
        return new CacheStats(
                blobs.estimatedSize(),
                playerStates.size(),
                blobs.stats()
        );
    }

    /**
     * Per-player cache state.
     * <p>
     * Tracks open transactions (blobs sent but not yet acknowledged) and generation counter
     * for staleness detection.
     */
    private static class PlayerCacheState {
        final List<LongOpenHashSet> openTransactions = new ArrayList<>();
        int generation = 0;
    }

    /**
     * Cache statistics.
     *
     * @param blobCount estimated number of blobs in the global cache
     * @param playerCount number of players with active cache state
     * @param caffeineStats detailed Caffeine cache statistics
     */
    public record CacheStats(
            long blobCount,
            int playerCount,
            com.github.benmanes.caffeine.cache.stats.CacheStats caffeineStats
    ) {}
}
