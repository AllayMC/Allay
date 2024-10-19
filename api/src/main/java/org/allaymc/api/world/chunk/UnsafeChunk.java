package org.allaymc.api.world.chunk;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.Map;

/**
 * The UnsafeChunk is located inside the {@link Chunk}, which is not thread-safe.
 * <p>
 * Compared to {@link Chunk}, unsafe chunk works more like a simple data container,
 * which means that it is very fast but is not thread-safe.
 * <p>
 * If you are sure that the instance won't be accessed by multiple threads,
 * you can operate on unsafe chunk directly. However, this may become very dangerous
 * if you do not have enough experience in multithreaded programming.
 *
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public interface UnsafeChunk {
    int SECTION_SIZE = 16 * 16 * 16;

    /**
     * Calculate the index of the pos in the chunk.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return the index of the pos in the chunk.
     */
    static int index(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= 0 && y <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        // The chunk order is x-z-y in bedrock edition, however the chunk order in java version is y-z-x
        return (x << 8) + (z << 4) + y;
    }

    /**
     * Get the state of the chunk.
     *
     * @return the state of the chunk.
     */
    ChunkState getState();

    /**
     * Get the dimension info of the chunk.
     *
     * @return the dimension info of the chunk.
     */
    DimensionInfo getDimensionInfo();

    /**
     * Get the x coordinate of the chunk.
     *
     * @return the x coordinate of the chunk.
     */
    int getX();

    /**
     * Get the z coordinate of the chunk.
     *
     * @return the z coordinate of the chunk.
     */
    int getZ();

    /**
     * Get the entity in the chunk by its runtime id.
     *
     * @param runtimeId the runtime id of the entity.
     *
     * @return the entity in the chunk, or {@code null} if not found.
     */
    Entity getEntity(long runtimeId);

    /**
     * Get all entities in the chunk.
     *
     * @return all entities in the chunk.
     */
    @UnmodifiableView
    Map<Long, Entity> getEntities();

    /**
     * Remove the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity.
     * @param y the y coordinate of the block entity.
     * @param z the z coordinate of the block entity.
     *
     * @return the removed block entity, or {@code null} if not found.
     */
    BlockEntity removeBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity.
     * @param y the y coordinate of the block entity.
     * @param z the z coordinate of the block entity.
     *
     * @return the block entity in this chunk, or {@code null} if not found.
     */
    BlockEntity getBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get all block entities in this chunk.
     *
     * @return all block entities in this chunk.
     */
    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    /**
     * Add a block entity to this chunk.
     *
     * @param blockEntity the block entity to add.
     */
    void addBlockEntity(BlockEntity blockEntity);

    /**
     * Gets Chunk section, range -1 -> -4, 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     *
     * @return the section
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(int sectionY);

    /**
     * Set block state in this chunk.
     *
     * @param x          the x coordinate of the block.
     * @param y          the y coordinate of the block.
     * @param z          the z coordinate of the block.
     * @param blockState the block state to set.
     * @param layer      the layer of the block.
     */
    void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Get block state in this chunk.
     *
     * @param x     the x coordinate of the block.
     * @param y     the y coordinate of the block.
     * @param z     the z coordinate of the block.
     * @param layer the layer of the block.
     *
     * @return the block state in this chunk.
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Set height of the specified position.
     *
     * @param x      the x coordinate of the position.
     * @param z      the z coordinate of the position.
     * @param height the height.
     */
    void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height);

    /**
     * Get height of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the height.
     */
    int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    /**
     * Set biome of the specified position.
     *
     * @param x         the x coordinate of the position.
     * @param y         the y coordinate of the position.
     * @param z         the z coordinate of the position.
     * @param biomeType the biome type.
     */
    void setBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    /**
     * Get biome of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param y the y coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the biome type.
     */
    BiomeType getBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Set skylight level of the specified position.
     *
     * @param x     the x coordinate of the position.
     * @param y     the y coordinate of the position.
     * @param z     the z coordinate of the position.
     * @param light the light level.
     */
    void setSkyLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int light);

    /**
     * Get skylight level of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param y the y coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the light level.
     */
    int getSkyLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Set block light level of the specified position.
     *
     * @param x     the x coordinate of the position.
     * @param y     the y coordinate of the position.
     * @param z     the z coordinate of the position.
     * @param light the light level.
     */
    void setBlockLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int light);

    /**
     * Get block light level of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param y the y coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the light level.
     */
    int getBlockLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get the hash of the chunk.
     *
     * @return the hash of the chunk.
     */
    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }
}
