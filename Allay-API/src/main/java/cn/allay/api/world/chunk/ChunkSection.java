package cn.allay.api.world.chunk;

import cn.allay.api.block.type.BlockState;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
public interface ChunkSection {
    int SECTION_SIZE = 16 * 16 * 16;

    BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, boolean layer);

    default BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        return getBlock(x, y, z, false);
    }

    void setBlock(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState);

    default void setBlock(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlock(x, y, z, false, blockState);
    }

    @Range(from = 0, to = 15) byte getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z);

    @Range(from = 0, to = 15) byte getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z);

    void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, byte light);

    void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z, byte light);
    //todo biome
}
