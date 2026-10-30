package org.allaymc.server.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.jpountz.xxhash.XXHash64;
import net.jpountz.xxhash.XXHashFactory;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheMissResponsePacket;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code ChunkCache} stores shared chunk blobs by their seeded XXHash64 content hash
 * and tracks unresolved cache transactions per player. Caffeine bounds global blob
 * storage, while each player state owns transaction sets and a generation used to
 * compare delayed encoders against dimension changes. Stored byte arrays remain
 * shared with their suppliers and must not be modified after registration.
 *
 * <p>
 * Registration checks generation and the transaction cap while holding that player
 * state's monitor, then stores blobs before recording their hashes as one
 * transaction. {@code clearPlayer} clears transactions and advances the same state's
 * generation; global blobs remain available to other players. This does not make
 * every operation one transaction: cache eviction and storage failures may leave
 * hashes without available blobs, generation reads are unsynchronized, and removing
 * a player can detach a state still referenced by in-flight work.
 *
 * @author daoge_cmd
 */
public final class ChunkCache {

    /**
     * {@code XXHASH64} computes the protocol blob identifiers with seed zero.
     * Registration and external callers use the same hash function for cache
     * references.
     */
    private static final XXHash64 XXHASH64 = XXHashFactory.fastestJavaInstance().hash64();
    /**
     * {@code instance} publishes the singleton cache through a volatile reference.
     * Explicit initialization and lazy fallback creation synchronize on
     * {@code ChunkCache.class}.
     */
    private static volatile ChunkCache instance;

    /**
     * {@code blobs} retains shared byte arrays by content hash. Caffeine may evict
     * entries independently of player transactions, and suppliers must preserve
     * stored bytes.
     */
    private final Cache<Long, byte[]> blobs;
    /**
     * {@code playerStates} associates UUIDs with mutable transaction state. Removing
     * an entry does not revoke a state already held by another operation.
     */
    private final ConcurrentHashMap<UUID, PlayerCacheState> playerStates;

    /**
     * Creates a shared blob cache bounded by the supplied maximum entry count. The
     * cache records Caffeine statistics and starts with no player states; invalid
     * negative capacities are rejected by its builder.
     */
    private ChunkCache(int maxBlobs) {
        this.blobs = Caffeine.newBuilder()
                .maximumSize(maxBlobs)
                .recordStats()
                .build();
        this.playerStates = new ConcurrentHashMap<>();
    }

    /**
     * Initializes the singleton cache before player caching begins. The class
     * monitor prevents competing initializers from publishing different instances;
     * an already initialized cache is rejected instead of replaced.
     *
     * @throws IllegalStateException if {@code instance} is already initialized
     */
    public static synchronized void initialize(int maxBlobs) {
        if (instance != null) {
            throw new IllegalStateException("ChunkCache already initialized");
        }
        instance = new ChunkCache(maxBlobs);
    }

    /**
     * Returns the published singleton cache, lazily creating it from server settings
     * when necessary. The initial volatile read avoids locking on the common
     * initialized path.
     *
     * <p>
     * The class monitor serializes fallback creation against explicit
     * initialization, and the second check retains whichever instance was published
     * first. A settings or construction failure propagates without assigning a
     * replacement instance, so a later call may retry initialization. The returned
     * cache remains shared for the server lifetime.
     */
    public static ChunkCache getInstance() {
        if (instance == null) {
            synchronized (ChunkCache.class) {
                if (instance == null) {
                    // Explicit initialization may not have run yet; use configured capacity while holding
                    // the same class monitor so only one fallback instance is published.
                    instance = new ChunkCache(AllayServer.getSettings().networkSettings().maxChunkCacheBlobs());
                }
            }
        }
        return instance;
    }

    /**
     * Returns the seed-zero XXHash64 value for all supplied blob bytes.
     */
    public static long computeHash(byte[] data) {
        return XXHASH64.hash(data, 0, data.length, 0);
    }

    /**
     * Returns the player's current state, creating an empty generation-zero state
     * when absent. The concurrent map coordinates insertion, while callers lock the
     * returned state for transaction and generation mutations.
     */
    private PlayerCacheState getPlayerState(UUID playerId) {
        return playerStates.computeIfAbsent(playerId, id -> new PlayerCacheState());
    }

    /**
     * Stores blob references and opens a player transaction only when the supplied
     * generation matches and fewer than {@code 512} transactions are unresolved. The
     * returned array preserves input order; rejection returns {@code null} without
     * registering a transaction. Empty {@code blobData} still adds an empty
     * transaction, consuming capacity until a later acknowledgement pass removes it
     * or player state is cleared.
     *
     * <p>
     * The player-state monitor serializes admission against acknowledgement and
     * clearing on that same state. Blob writes precede transaction creation and
     * retain the supplied arrays, so callers must stop modifying them. A hash or
     * cache failure may leave earlier global blob writes present without a
     * corresponding transaction; eviction can also remove a blob before a later miss
     * request.
     */
    public long[] tryStoreBlobsAndOpenTransaction(UUID playerId, int expectedGeneration, byte[]... blobData) {
        PlayerCacheState state = getPlayerState(playerId);

        synchronized (state) {
            // Reject an encoder that observed an earlier generation before clearing acquired this same
            // state monitor, so it cannot register those hashes in the retained new generation.
            if (state.generation != expectedGeneration) {
                return null;
            }

            // Bound unresolved transactions per player so one client cannot retain an unbounded number
            // of transaction sets while acknowledgements fail to arrive.
            if (state.openTransactions.size() >= 512) {
                return null;
            }

            // Register shared byte arrays before opening the transaction; a failure during these writes
            // can leave earlier blobs cached without a transaction recording them.
            long[] hashes = new long[blobData.length];
            for (int i = 0; i < blobData.length; i++) {
                hashes[i] = computeHash(blobData[i]);
                blobs.put(hashes[i], blobData[i]);
            }

            // Even an empty input opens a transaction; it consumes admission capacity until an
            // acknowledgement traversal or player-state clear removes that empty set.
            var transaction = new LongOpenHashSet(hashes);
            state.openTransactions.add(transaction);

            return hashes;
        }
    }

    /**
     * Resolves acknowledged hashes in the player's open transactions and encodes
     * available negative-acknowledgement blobs. Acknowledgements remove a hash from
     * every matching transaction, and an emptied transaction is released while
     * holding the player-state monitor.
     *
     * <p>
     * Negative acknowledgements read global cache entries after that monitor is
     * released, so eviction may omit some requested hashes. No missing requests or
     * no available blobs returns {@code null}; otherwise the caller owns the
     * returned reference-counted packet and must send or release it. The protocol
     * encoder is responsible for copying bytes into its packet buffers.
     */
    public ClientCacheMissResponsePacket handleBlobStatus(
            PacketEncoder encoder,
            UUID playerId,
            long[] acks,
            long[] naks
    ) {
        Objects.requireNonNull(encoder, "encoder");
        PlayerCacheState state = getPlayerState(playerId);

        synchronized (state) {
            // Resolve each acknowledged hash in every transaction under the state monitor so an empty
            // transaction releases capacity before a later registration checks the cap.
            for (long ack : acks) {
                state.openTransactions.removeIf(transaction -> {
                    transaction.remove(ack);
                    return transaction.isEmpty();
                });
            }
        }

        // Cache reads can proceed without the player monitor; eviction may remove requested data, so
        // the response includes only hashes still available in global storage.
        if (naks.length == 0) {
            return null;
        }

        var missBlobs = new LinkedHashMap<Long, byte[]>();

        for (long nak : naks) {
            byte[] blobData = blobs.getIfPresent(nak);
            if (blobData != null) {
                missBlobs.put(nak, blobData);
            }
        }

        return missBlobs.isEmpty() ? null : encoder.encodeClientCacheMissResponse(missBlobs);
    }

    /**
     * Clears transactions and advances the generation when a state already exists
     * for the UUID. The state monitor serializes this update against registration
     * and acknowledgement on the same object.
     *
     * <p>
     * Global blobs are retained because other players may use them. A missing state
     * is not created, and a delayed caller holding an earlier generation is rejected
     * only when it reaches this retained state with a mismatching generation; this
     * method does not cancel work or revoke detached state references.
     */
    public void clearPlayer(UUID playerId) {
        PlayerCacheState state = playerStates.get(playerId);
        if (state != null) {
            // Clear pending transactions and advance their generation together so registration on this
            // same state cannot interleave between those two changes.
            synchronized (state) {
                state.openTransactions.clear();
                state.generation++;
            }
        }
    }

    /**
     * Removes the UUID's current state mapping while retaining shared global blobs.
     * An operation already holding that state may continue, and later lookup can
     * create a new generation-zero state for the same UUID.
     */
    public void removePlayer(UUID playerId) {
        playerStates.remove(playerId);
    }

    /**
     * Returns the player's generation, creating an empty state when necessary. This
     * read does not acquire the state monitor used by generation updates, so callers
     * must not treat it as a synchronized snapshot.
     */
    public int getGeneration(UUID playerId) {
        return getPlayerState(playerId).generation;
    }

    /**
     * Returns estimated blob count, current player-state count, and Caffeine
     * statistics. These observations are collected separately while other operations
     * may continue, so the record is not an atomic cache snapshot.
     */
    public CacheStats getStats() {
        return new CacheStats(
                blobs.estimatedSize(),
                playerStates.size(),
                blobs.stats()
        );
    }

    /**
     * {@code PlayerCacheState} holds one player's unresolved blob transactions and
     * generation counter. Registration, acknowledgement, and clearing mutate these
     * values under the state monitor; generation reads currently occur without that
     * monitor.
     */
    private static class PlayerCacheState {
        /**
         * {@code openTransactions} contains hash sets awaiting acknowledgements.
         * Transaction mutations use the owning state monitor, and duplicate input
         * hashes share one set entry.
         */
        final List<LongOpenHashSet> openTransactions = new ArrayList<>();
        /**
         * {@code generation} changes when existing state is cleared. Its updates use
         * the state monitor, while {@code getGeneration} reads it without
         * synchronization.
         */
        int generation = 0;
    }

    /**
     * {@code CacheStats} holds separately observed cache sizes and Caffeine
     * counters. The record does not promise that its counts describe one instant
     * while concurrent cache operations continue.
     *
     * @param blobCount estimated global blob entry count
     * @param playerCount current mapped player-state count
     * @param caffeineStats Caffeine cache counter snapshot
     */
    public record CacheStats(
            long blobCount,
            int playerCount,
            com.github.benmanes.caffeine.cache.stats.CacheStats caffeineStats
    ) {}
}
