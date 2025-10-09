package org.allaymc.api.world.generator.context;

import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public abstract class OtherChunkAccessibleContext extends Context {
    private static final BlockState AIR = BlockTypes.AIR.getDefaultState();

    /**
     * The chunk source to get chunks from if the requested chunk is not the current chunk.
     * The implementation of this class will control how many chunks this context can access.
     */
    @Getter
    protected ChunkSource chunkSource;

    public OtherChunkAccessibleContext(UnsafeChunk currentChunk, ChunkSource chunkSource) {
        super(currentChunk);
        this.chunkSource = chunkSource;
    }

    /**
     * Sets the block state at the specified coordinates in the default layer. If the coordinates are in the current
     * chunk, sets it directly; otherwise, delegates to setBlockStateInOtherChunk.
     *
     * @param x          The x-coordinate (world)
     * @param y          The y-coordinate (world)
     * @param z          The z-coordinate (world)
     * @param blockState The block state to set
     */
    public void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Sets the block state at the specified coordinates and layer. If the coordinates are in the current chunk,
     * sets it directly; otherwise, delegates to setBlockStateInOtherChunk.
     *
     * @param x          The x-coordinate (world)
     * @param y          The y-coordinate (world)
     * @param z          The z-coordinate (world)
     * @param blockState The block state to set
     * @param layer      The block layer
     */
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        if (isInCurrentChunk(x, y, z)) {
            currentChunk.setBlockState(x & 15, y, z & 15, blockState, layer);
            return;
        }

        setBlockStateInOtherChunk(x, y, z, blockState, layer);
    }

    /**
     * Gets the block state at the specified coordinates in the default layer.
     *
     * @param x The x-coordinate (world)
     * @param y The y-coordinate (world)
     * @param z The z-coordinate (world)
     * @return The block state at the given position
     */
    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Gets the block state at the specified coordinates and layer. If the coordinates are in the current
     * chunk, gets it directly; otherwise, delegates to getBlockStateInOtherChunk.
     *
     * @param x     The x-coordinate (world)
     * @param y     The y-coordinate (world)
     * @param z     The z-coordinate (world)
     * @param layer The block layer
     * @return The block state at the given position and layer
     */
    public BlockState getBlockState(int x, int y, int z, int layer) {
        return isInCurrentChunk(x, y, z) ?
                currentChunk.getBlockState(x & 15, y, z & 15, layer) :
                getBlockStateInOtherChunk(x, y, z, layer);
    }

    /**
     * Retrieves the {@code BlockEntity} at the specified world coordinates.
     * If the coordinates refer to a block in the current chunk, the {@code BlockEntity}
     * is fetched directly from the current chunk. Otherwise, it is fetched
     * from the appropriate chunk using other chunk access.
     *
     * @param x The x-coordinate (world).
     * @param y The y-coordinate (world).
     * @param z The z-coordinate (world).
     * @return The {@code BlockEntity} at the specified coordinates, or {@code null} if no
     * {@code BlockEntity} exists at that position.
     */
    public BlockEntity getBlockEntity(int x, int y, int z) {
        return isInCurrentChunk(x, y, z) ?
                currentChunk.getBlockEntity(x & 15, y, z & 15) :
                getBlockEntityInOtherChunk(x, y, z);
    }

    private void setBlockStateInOtherChunk(int x, int y, int z, BlockState blockState, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        if (chunk == null) {
            return;
        }

        chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
    }

    private BlockState getBlockStateInOtherChunk(int x, int y, int z, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk == null ? AIR : chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    private BlockEntity getBlockEntityInOtherChunk(int x, int y, int z) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk == null ? null : chunk.getBlockEntity(x & 15, y, z & 15);
    }

    private boolean isInCurrentChunk(int x, int y, int z) {
        var currentChunkX = currentChunk.getX();
        var currentChunkZ = currentChunk.getZ();
        var dimInfo = currentChunk.getDimensionInfo();
        return
                x >= currentChunkX * 16 && x < currentChunkX * 16 + 16 &&
                z >= currentChunkZ * 16 && z < currentChunkZ * 16 + 16 &&
                y >= dimInfo.minHeight() && y <= dimInfo.maxHeight();
    }
}
