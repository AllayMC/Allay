package org.allaymc.api.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.poi.PoiType;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Unsafe is similar to {@link Chunk} but is not thread-safe.
 * <p>
 * Compared to {@link Chunk}, unsafe chunk is very fast but is not thread-safe.
 * All the methods in this class should be considered as thread-unsafe, although
 * some of them may be thread-safe depending on the implementation. If you are sure
 * that the instance won't be accessed by multiple threads, you can operate on unsafe
 * chunk directly. However, this may become very dangerous if you do not have enough
 * experience in multithreaded programming.
 *
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public interface UnsafeChunk {

    /**
     * Check if the chunk is loaded.
     *
     * @return {@code true} if the chunk is loaded, {@code false} otherwise.
     */
    boolean isLoaded();

    /**
     * Get the chunk loaders that load this chunk
     *
     * @return the chunk loaders
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * For-each the chunk loaders of the chunk.
     *
     * @param consumer the consumer to be applied to each chunk loader
     */
    default void forEachChunkLoaders(Consumer<ChunkLoader> consumer) {
        getChunkLoaders().forEach(consumer);
    }

    /**
     * For-each the chunk loaders of the chunk in the next chunk tick.
     *
     * @param consumer the consumer to be applied to each chunk loader
     */
    default void forEachChunkLoaderLater(Consumer<ChunkLoader> consumer) {
        addChunkTask(() -> forEachChunkLoaders(consumer));
    }

    /**
     * Add a chunk loader to this chunk.
     *
     * @param chunkLoader the chunk loader to add
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Remove a chunk loader from this chunk.
     *
     * @param chunkLoader the chunk loader to remove
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * Get the number of chunk loaders that load this chunk.
     *
     * @return the number of chunk loaders
     */
    default int getChunkLoaderCount() {
        return getChunkLoaders().size();
    }

    /**
     * Adds a chunk task to the chunk. The task will be executed in the next chunk tick.
     *
     * @param task the task to execute
     */
    void addChunkTask(Runnable task);

    /**
     * Get the state of the chunk.
     *
     * @return the state of the chunk
     */
    ChunkState getState();

    /**
     * Get the dimension info of the chunk.
     *
     * @return the dimension info of the chunk
     */
    DimensionInfo getDimensionInfo();

    /**
     * Get the x coordinate of the chunk.
     *
     * @return the x coordinate of the chunk
     */
    int getX();

    /**
     * Get the z coordinate of the chunk.
     *
     * @return the z coordinate of the chunk
     */
    int getZ();

    /**
     * Remove the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity
     * @param y the y coordinate of the block entity
     * @param z the z coordinate of the block entity
     * @return the removed block entity, or {@code null} if not found
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    BlockEntity removeBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity
     * @param y the y coordinate of the block entity
     * @param z the z coordinate of the block entity
     * @return the block entity in this chunk, or {@code null} if not found
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    BlockEntity getBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get all block entities in this chunk.
     *
     * @return all block entities in this chunk
     */
    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    /**
     * Add a block entity to this chunk.
     *
     * @param blockEntity the block entity to add
     */
    void addBlockEntity(BlockEntity blockEntity);

    /**
     * Gets Chunk section, range -1 -> -4, 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y value of the section
     * @return the section
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(int sectionY);

    /**
     * Add a scheduled update to a pos in the chunk.
     *
     * @param x    the x coordinate of the pos
     * @param y    the y coordinate of the pos
     * @param z    the z coordinate of the pos
     * @param time the scheduled time of the scheduled update
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, long time);

    /**
     * Check if a pos in the chunk has a scheduled update.
     *
     * @param x the x coordinate of the pos
     * @param y the y coordinate of the pos
     * @param z the z coordinate of the pos
     * @return {@code true} if the pos has a scheduled update, otherwise {@code false}.
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Set block state in this chunk.
     *
     * @param x          the x coordinate of the block
     * @param y          the y coordinate of the block
     * @param z          the z coordinate of the block
     * @param blockState the block state to set
     * @param layer      the layer of the block
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer, boolean send);

    /**
     * @see #setBlockState(int, int, int, BlockState, int)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer) {
        setBlockState(x, y, z, blockState, layer, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Get block state in this chunk.
     *
     * @param x     the x coordinate of the block
     * @param y     the y coordinate of the block
     * @param z     the z coordinate of the block
     * @param layer the layer of the block
     * @return the block state in this chunk, or {@code BlockTypes.AIR.getDefaultState()} if y is out of range
     * @throws IllegalArgumentException if x or z is out of range
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Get height of the specified position. If there are no blocks in the (x, z) position, the height will be the min height of the current dimension
     *
     * @param x the x coordinate of the position
     * @param z the z coordinate of the position
     * @return the height
     * @throws IllegalArgumentException if x or z is out of range
     */
    short getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    /**
     * Set biome of the specified position.
     *
     * @param x         the x coordinate of the position
     * @param y         the y coordinate of the position
     * @param z         the z coordinate of the position
     * @param biomeType the biome type
     * @throws IllegalArgumentException if x, y or z is out of range
     */
    void setBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    /**
     * Get biome of the specified position.
     *
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     * @param z the z coordinate of the position
     * @return the biome type, or {@link org.allaymc.api.world.biome.BiomeTypes#PLAINS} if y is out of range
     * @throws IllegalArgumentException if x or z is out of range
     */
    BiomeType getBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get a specific chunk section in this chunk.
     *
     * @param sectionY the sectionY of the chunk section
     * @return the section, should never be {@code null}
     * @throws IllegalArgumentException if section y is out of range
     */
    ChunkSection getSection(int sectionY);

    /**
     * Get all chunk sections in this chunk.
     *
     * @return all chunk sections in this chunk
     */
    @UnmodifiableView
    List<ChunkSection> getSections();

    /**
     * Get the hash of the chunk.
     *
     * @return the hash of the chunk
     */
    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    /**
     * Get all POI entries in this chunk.
     *
     * @return all POI entries, keyed by {@link HashUtils#hashChunkXYZ(int, int, int)}
     */
    Map<Integer, PoiType> getPoiEntries();

    /**
     * Add a POI entry to this chunk.
     *
     * @param x    the chunk-local x coordinate (0-15)
     * @param y    the world y coordinate
     * @param z    the chunk-local z coordinate (0-15)
     * @param type the POI type
     */
    void addPoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, PoiType type);

    /**
     * Remove a POI entry from this chunk.
     *
     * @param x the chunk-local x coordinate (0-15)
     * @param y the world y coordinate
     * @param z the chunk-local z coordinate (0-15)
     */
    void removePoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get the POI type at the given position, or {@code null} if none.
     *
     * @param x the chunk-local x coordinate (0-15)
     * @param y the world y coordinate
     * @param z the chunk-local z coordinate (0-15)
     * @return the POI type, or {@code null}
     */
    PoiType getPoi(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Convert this unsafe chunk to a {@link Chunk} which is safe in multithreaded environment.
     *
     * @return the safe chunk
     */
    Chunk toSafeChunk();
}
