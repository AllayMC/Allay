package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDirtWithRoots extends Block {
    BlockType<BlockDirtWithRoots> TYPE = BlockTypeBuilder
            .builder(BlockDirtWithRoots.class)
            .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS, true)
            .addBasicComponents()
            .build();
}
