package org.allaymc.api.world.storage;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.jetbrains.annotations.ApiStatus;

import java.util.concurrent.CompletableFuture;

/**
 * WorldStorage represents the storage of a world, which is responsible for reading and writing chunks and world data.
 * The instance of WorldStorage will be created for each world and won't be shared between worlds.
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface WorldStorage {
    /**
     * This method will be called every world tick.
     *
     * @param currentTick the current world tick.
     */
    @ApiStatus.OverrideOnly
    default void tick(long currentTick) {}

    /**
     * This method will be called before the first tick of the world. The implementation can decide
     * whether to hold the world object (if the implementation needs the world object during runtime).
     * Note that user is not allowed to call this method.
     *
     * @param world the world object.
     */
    @ApiStatus.OverrideOnly
    default void setWorld(World world) {}

    /**
     * Shutdown the storage.
     */
    default void shutdown() {}

    /**
     * Read a chunk from the storage.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     *
     * @return a chunk future
     */
    CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Read a chunk from the storage synchronously.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     *
     * @return the chunk
     */
    Chunk readChunkSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo);

    /**
     * Write a chunk to the storage.
     *
     * @param chunk the chunk to write
     *
     * @return a future which will be completed when the chunk is written
     */
    CompletableFuture<Void> writeChunk(Chunk chunk);

    /**
     * Write a chunk to the storage synchronously.
     *
     * @param chunk the chunk to write
     */
    void writeChunkSync(Chunk chunk);

    /**
     * Check if the storage contains a chunk.
     *
     * @param chunkX        the x coordinate of the chunk
     * @param chunkZ        the z coordinate of the chunk
     * @param dimensionInfo the dimension info of the chunk
     *
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
}
