package org.allaymc.api.world.generator.context;

import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * @author daoge_cmd
 */
public abstract class OtherChunkAccessibleContext extends Context {
    private static final BlockState AIR = BlockTypes.AIR.getDefaultState();

    @Getter
    protected ChunkSource chunkSource;

    public OtherChunkAccessibleContext(UnsafeChunk currentChunk, ChunkSource chunkSource) {
        super(currentChunk);
        this.chunkSource = chunkSource;
    }

    public void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        if (isInCurrentChunk(x, y, z)) {
            currentChunk.setBlockState(x & 15, y, z & 15, blockState, layer);
            return;
        }

        setBlockStateInOtherChunk(x, y, z, blockState, layer);
    }

    private void setBlockStateInOtherChunk(int x, int y, int z, BlockState blockState, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        if (chunk == null) return;

        chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
    }

    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        return isInCurrentChunk(x, y, z) ?
                currentChunk.getBlockState(x & 15, y, z & 15, layer) :
                getBlockStateInOtherChunk(x, y, z, layer);
    }

    private BlockState getBlockStateInOtherChunk(int x, int y, int z, int layer) {
        var chunk = chunkSource.getChunk(x >> 4, z >> 4);
        return chunk == null ? AIR : chunk.getBlockState(x & 15, y, z & 15, layer);
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
