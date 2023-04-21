package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrassPath extends Block {
    BlockType<BlockGrassPath> TYPE = BlockTypeBuilder
            .builder(BlockGrassPath.class)
            .vanillaBlock(VanillaBlockId.GRASS_PATH)
            .build();
}
