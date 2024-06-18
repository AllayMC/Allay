package org.allaymc.api.world.generator.context;

import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.chunk.ChunkAccessible;
import org.allaymc.api.world.chunk.UnsafeChunk;

/**
 * Allay Project 2024/6/16
 *
 * @author daoge_cmd
 */
public abstract class OtherChunkAccessibleContext extends Context {

    @Getter
    protected ChunkAccessible chunkAccessor;

    public OtherChunkAccessibleContext(UnsafeChunk currentChunk, ChunkAccessible chunkAccessor) {
        super(currentChunk);
        this.chunkAccessor = chunkAccessor;
    }

    public void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        if(isInCurrentChunk(x, y, z)) {
            currentChunk.setBlockState(x & 15, y, z & 15, blockState, layer);
            return;
        }
        setBlockStateInOtherChunk(x, y, z, blockState, layer);
    }

    private void setBlockStateInOtherChunk(int x, int y, int z, BlockState blockState, int layer) {
        var chunk = chunkAccessor.getChunk(x >> 4, z >> 4);
        chunk.setBlockState(x & 15, y, z & 15, blockState, layer);
    }

    public BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        if(isInCurrentChunk(x, y, z)) {
            return currentChunk.getBlockState(x & 15, y, z & 15, layer);
        }
        return getBlockStateInOtherChunk(x, y, z, layer);
    }

    private BlockState getBlockStateInOtherChunk(int x, int y, int z, int layer) {
        var chunk = chunkAccessor.getChunk(x >> 4, z >> 4);
        return chunk.getBlockState(x & 15, y, z & 15);
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
