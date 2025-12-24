package org.allaymc.api.world.storage;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.DimensionInfo;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * WorldStorage represents the storage of a world, which is responsible for reading and writing chunks and world data.
 * The instance of WorldStorage will be created for each world and won't be shared between worlds.
 * <p>
 * The implementation of these methods should all be thread-safe.
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface WorldStorage {
    /**
     * This method will be called every world tick.
     *
     * @param currentTick the current world tick
     */
    @ApiStatus.OverrideOnly
    default void tick(long currentTick) {
    }

    /**
     * This method will be called before the first tick of the world. The implementation can decide
     * whether to hold the world object (if the implementation needs the world object during runtime).
     * Note that user is not allowed to call this method.
     *
     * @param world the world object
     */
    @ApiStatus.OverrideOnly
    default void setWorld(World world) {
    }

    /**
     * Shutdown the storage.
     */
    default void shutdown() {
    }

    /**
     * Read a chunk from the storage.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     * @return a chunk future
     */
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Similar to {@link #readChunk(int, int, DimensionInfo)} but is synchronous.
     */
    Chunk readChunkSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Write a chunk to the storage.
     *
     * @param chunk the chunk to write
     * @return a future which will be completed when the chunk is written
     */
    CompletableFuture<Void> writeChunk(Chunk chunk);

    /**
     * Similar to {@link #writeChunk(Chunk)} but is synchronous.
     */
    void writeChunkSync(Chunk chunk);

    /**
     * Read the entities in a chunk.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     * @return the entities in the chunk
     */
    CompletableFuture<Map<Long, Entity>> readEntities(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Similar to {@link #readEntities(int, int, DimensionInfo)} but is synchronous.
     */
    Map<Long, Entity> readEntitiesSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Write the entities in a chunk.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     * @param entities      the entities to write. Note that the key is the unique id of the entity, not the runtime id
     *                      If an empty map is passed, all existing entities in the chunk will be removed.
     * @return a future which will be completed when the entities are written
     */
    CompletableFuture<Void> writeEntities(int chunkX, int chunkZ, DimensionInfo dimensionInfo, Map<Long, Entity> entities);

    /**
     * Similar to {@link #writeEntities(int, int, DimensionInfo, Map)} but is synchronous.
     */
    void writeEntitiesSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo, Map<Long, Entity> entities);

    /**
     * Check if the storage contains a chunk.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     * @return {@code true} if the storage contains the chunk, {@code false} otherwise
     */
    boolean containChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Write world data to the storage.
     *
     * @param worldData the world data to write
     */
    void writeWorldData(WorldData worldData);

    /**
     * Read world data from the storage.
     *
     * @return the world data
     */
    WorldData readWorldData();

    /**
     * Retrieves the name associated with the world storage. Which should be the same
     * registered in {@link Registries#WORLD_STORAGE_FACTORIES}.
     *
     * @return the name of the world storage
     */
    String getName();
}
