package org.allaymc.api.world.feature;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSource;

/**
 * {@code WorldFeatureContext} routes feature block access through a supplied
 * chunk source using dimension coordinates. It converts horizontal positions
 * to local chunk coordinates and preserves the source's availability rules;
 * unavailable chunks yield air or {@code null} reads and ignore writes.
 *
 * @author daoge_cmd
 */
public class WorldFeatureContext implements ChunkSource {

    /**
     * {@code chunkSource} is retained for feature access. Its own availability
     * and synchronization rules apply to every lookup and block operation.
     */
    private final ChunkSource chunkSource;

    /**
     * Creates a feature context retaining the supplied chunk source. The
     * caller must arrange the intended access region and synchronization
     * because this wrapper neither reserves chunks nor loads missing neighbors
     * itself.
     */
    public WorldFeatureContext(ChunkSource chunkSource) {
        this.chunkSource = chunkSource;
    }

    /**
     * Returns the chunk exposed by the retained source at chunk coordinates.
     * The returned reference and missing-chunk behavior belong to that source;
     * this wrapper adds no loading or reservation.
     */
    @Override
    public Chunk getChunk(int x, int z) {
        return chunkSource.getChunk(x, z);
    }

    /**
     * Replaces the primary block layer at dimension coordinates through the
     * retained source. This overload selects layer zero, and an unavailable
     * chunk causes the write to be ignored.
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
     * Replaces the requested layer at dimension coordinates when the source
     * exposes the containing chunk. Available chunks enforce their normal
     * height and layer rules; missing chunks discard the write.
     *
     * @param x          the x coordinate (world position)
     * @param y          the y coordinate (world position)
     * @param z          the z coordinate (world position)
     * @param blockState the block state to set
     * @param layer      the layer ({@code 0} for main, {@code 1} for liquid layer)
     */
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        // Select the containing chunk even for negative coordinates. An
        // unavailable chunk discards this write rather than creating terrain
        // outside the source.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        if (chunk != null) {
            chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
        }
    }

    /**
     * Returns the primary block state at dimension coordinates through the
     * retained source. This overload selects layer zero, and unavailable chunks
     * return the default air state instead of generating terrain.
     *
     * @param x the x coordinate (world position)
     * @param y the y coordinate (world position)
     * @param z the z coordinate (world position)
     * @return the block state, or the default air state for an unavailable chunk
     */
    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Returns the selected block layer at dimension coordinates through the
     * retained source. Missing chunks return air, while available chunks apply
     * their normal height and layer handling.
     *
     * @param x     the x coordinate (world position)
     * @param y     the y coordinate (world position)
     * @param z     the z coordinate (world position)
     * @param layer the layer
     * @return the block state, or the default air state for an unavailable chunk
     */
    public BlockState getBlockState(int x, int y, int z, int layer) {
        // Select the containing chunk before masking local coordinates. Missing
        // terrain becomes air, so a feature cannot distinguish actual air from an
        // unavailable neighbor.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk != null ? chunk.getBlockState(x & 15, y, z & 15, layer) : BlockTypes.AIR.getDefaultState();
    }

    /**
     * Returns the shared block entity at dimension coordinates, or {@code null}
     * for an absent entity or unavailable chunk. Available chunks still enforce
     * their own coordinate validation, including height bounds.
     *
     * @param x the x coordinate (world position)
     * @param y the y coordinate (world position)
     * @param z the z coordinate (world position)
     * @return the shared block entity, or {@code null} when absent or unavailable
     */
    public BlockEntity getBlockEntity(int x, int y, int z) {
        // Select the containing chunk before masking local coordinates. An
        // unavailable neighbor has no accessible entity map and therefore shares
        // the absent-entity result.
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk != null ? chunk.getBlockEntity(x & 15, y, z & 15) : null;
    }
}
