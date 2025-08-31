package org.allaymc.api.world.chunk;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.Range;

/**
 * Represents a 16*16*16 area in a {@link Chunk}. Note that all methods in this class is not thread-safe.
 *
 * @author daoge_cmd
 */
@NotThreadSafe
public interface ChunkSection {

    /**
     * Get the block state at the given local position.
     *
     * @param x     the local x coordinate of the block state
     * @param y     the local y coordinate of the block state
     * @param z     the local z coordinate of the block state
     * @param layer the layer of the block state
     *
     * @return the block state at the given position
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * Set the block state at the given local position.
     *
     * @param x          the local x coordinate of the block state
     * @param y          the local y coordinate of the block state
     * @param z          the local z coordinate of the block state
     * @param blockState the block state to set
     * @param layer      the layer of the block state
     */
    void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    /**
     * Get the biome type at the given local position.
     *
     * @param x the local x coordinate of the biome type
     * @param y the local y coordinate of the biome type
     * @param z the local z coordinate of the biome type
     *
     * @return the biome type at the given position
     */
    BiomeType getBiomeType(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z);

    /**
     * Set the biome type at the given local position.
     *
     * @param x         the local x coordinate of the biome type
     * @param y         the local y coordinate of the biome type
     * @param z         the local z coordinate of the biome type
     * @param biomeType the biome type to set
     */
    void setBiomeType(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);
}
