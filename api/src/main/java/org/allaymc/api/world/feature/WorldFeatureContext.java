package org.allaymc.api.world.feature;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSource;

/**
 * WorldFeatureContext provides context for feature placement.
 * It wraps a ChunkSource to provide block access operations.
 *
 * @author daoge_cmd
 */
public class WorldFeatureContext implements ChunkSource {

    private final ChunkSource chunkSource;

    public WorldFeatureContext(ChunkSource chunkSource) {
        this.chunkSource = chunkSource;
    }

    @Override
    public Chunk getChunk(int x, int z) {
        return chunkSource.getChunk(x, z);
    }

    /**
     * Set the block state at the specified position in the default layer.
     *
     * @param x          the x coordinate (world position)
     * @param y          the y coordinate (world position)
     * @param z          the z coordinate (world position)
     * @param blockState the block state to set
     */
    public void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Set the block state at the specified position and layer.
     *
     * @param x          the x coordinate (world position)
     * @param y          the y coordinate (world position)
     * @param z          the z coordinate (world position)
     * @param blockState the block state to set
     * @param layer      the layer (0 for main, 1 for liquid layer)
     */
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        if (chunk != null) {
            chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
        }
    }

    /**
     * Get the block state at the specified position in the default layer.
     *
     * @param x the x coordinate (world position)
     * @param y the y coordinate (world position)
     * @param z the z coordinate (world position)
     * @return the block state, or AIR if not found
     */
    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Get the block state at the specified position and layer.
     *
     * @param x     the x coordinate (world position)
     * @param y     the y coordinate (world position)
     * @param z     the z coordinate (world position)
     * @param layer the layer
     * @return the block state, or AIR if not found
     */
    public BlockState getBlockState(int x, int y, int z, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk != null ? chunk.getBlockState(x & 15, y, z & 15, layer) : BlockTypes.AIR.getDefaultState();
    }

    /**
     * Get the block entity at the specified position.
     *
     * @param x the x coordinate (world position)
     * @param y the y coordinate (world position)
     * @param z the z coordinate (world position)
     * @return the block entity, or null if not found
     */
    public BlockEntity getBlockEntity(int x, int y, int z) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk != null ? chunk.getBlockEntity(x & 15, y, z & 15) : null;
    }
}
