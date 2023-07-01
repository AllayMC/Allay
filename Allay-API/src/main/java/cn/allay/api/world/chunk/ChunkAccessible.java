package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface ChunkAccessible {
    @Nullable
    Chunk getChunk(int x, int z);

    void setChunk(int x, int z, Chunk chunk);

    @Nullable
    default BlockState getBlock(int x, @Range(from = -512, to = 511) int y, int z, boolean layer) {
        Chunk chunk = getChunk(x >> 4, z >> 4);
        if (chunk == null) {
            return null;
        }
        return chunk.getBlock(x & 15, y, z & 15, layer);
    }

    default BlockState getBlock(int x, @Range(from = -512, to = 511) int y, int z) {
        return getBlock(x, y, z, false);
    }

    int maxX();

    int maxZ();

    int minX();

    int minZ();
}
