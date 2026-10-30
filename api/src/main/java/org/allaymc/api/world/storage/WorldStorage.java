package org.allaymc.api.world.storage;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.dimension.DimensionType;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * {@code WorldStorage} stores
 * one world's chunks, entities, and world metadata behind a common
 * persistence contract. Each world receives its own storage instance, and
 * implementations must make their operations thread-safe. World lifecycle hooks
 * establish the owning world before ticking and release storage resources at
 * shutdown.
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface WorldStorage {
    /**
     * Performs storage maintenance for the current world tick. The world
     * invokes this hook once per tick; the default implementation performs no
     * work and retains no tick state.
     */
    @ApiStatus.OverrideOnly
    default void tick(long currentTick) {
    }

    /**
     * Associates the storage with the world that owns it before the first world
     * tick. This hook is reserved for the world lifecycle; the default
     * implementation does not retain the supplied reference.
     */
    @ApiStatus.OverrideOnly
    default void setWorld(World world) {
    }

    /**
     * Starts a batch in which chunk writes accumulate until the matching flush.
     * Implementations supporting batching combine those writes into one I/O
     * operation; the default hook has no effect.
     */
    @ApiStatus.OverrideOnly
    default void startBatchWrite() {
    }

    /**
     * Flushes accumulated chunk writes and exits batch mode after a preceding
     * start request. Implementations supporting batching submit the accumulated
     * writes together; the default hook performs no persistence work.
     */
    @ApiStatus.OverrideOnly
    default void flushBatchWrite() {
    }

    /**
     * Releases storage resources when the owning world stops using this
     * instance. The default implementation has no resources to release;
     * implementations define the handling of pending work during shutdown.
     */
    default void shutdown() {
    }

    /**
     * Reads the chunk at the supplied chunk coordinates in the requested
     * dimension. The returned future delivers the loaded chunk; implementations
     * document their handling of absent data and read failures.
     */
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionType dimensionType);

    /**
     * Reads the requested chunk on the calling thread and returns the loaded
     * result. Coordinates identify chunks rather than individual blocks;
     * implementations define how absent or unreadable data is handled.
     */
    Chunk readChunkSync(int chunkX, int chunkZ, DimensionType dimensionType);

    /**
     * Writes the supplied chunk and returns a future that completes when the
     * chunk is written. Callers use that completion to observe the write
     * operation described by this storage contract.
     */
    CompletableFuture<Void> writeChunk(Chunk chunk);

    /**
     * Writes the supplied chunk synchronously, completing the write before this
     * method returns. This is the calling-thread counterpart of {@link
     * #writeChunk(Chunk)} and exposes failures through the implementation's
     * synchronous error handling.
     */
    void writeChunkSync(Chunk chunk);

    /**
     * Reads the entities stored for the requested chunk and dimension. The
     * future supplies a map keyed by persistent entity identifiers; these keys
     * identify stored entities rather than network runtime instances.
     */
    CompletableFuture<Map<Long, Entity>> readEntities(int chunkX, int chunkZ, DimensionType dimensionType);

    /**
     * Reads the requested chunk's entities on the calling thread and returns
     * their persistent identifier mapping. Implementations define recovery for
     * missing entity records and unsupported serialized entity types.
     */
    Map<Long, Entity> readEntitiesSync(int chunkX, int chunkZ, DimensionType dimensionType);

    /**
     * Replaces the stored entities for the requested chunk and dimension with
     * the supplied mapping. Keys are entity unique identifiers, not runtime
     * identifiers; an empty map removes existing entities. The returned future
     * completes when the entities are written.
     */
    CompletableFuture<Void> writeEntities(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities);

    /**
     * Replaces the requested chunk's stored entities before returning on the
     * calling thread. Keys use unique identifiers rather than runtime
     * identifiers, and an empty mapping removes the entities previously stored
     * for that chunk.
     */
    void writeEntitiesSync(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities);

    /**
     * Returns whether storage contains chunk data at the supplied chunk
     * coordinates in the requested dimension. The result concerns persisted
     * data; implementations define which stored records establish that a chunk
     * exists.
     */
    boolean containChunk(int chunkX, int chunkZ, DimensionType dimensionType);

    /**
     * Writes the supplied world metadata to this storage instance.
     * Implementations define their supported metadata representation and
     * failure behavior; this operation is separate from writing individual
     * chunks and their entities.
     */
    void writeWorldData(WorldData worldData);

    /**
     * Reads the world metadata associated with this storage instance.
     * Implementations define their defaults for a new world and the failure
     * behavior for unreadable or unsupported stored metadata.
     */
    WorldData readWorldData();

    /**
     * Returns the storage factory name used to identify this implementation in
     * the world storage registry. The value must match the name registered in
     * {@link Registries#WORLD_STORAGE_FACTORIES} for this implementation.
     */
    String getName();
}
