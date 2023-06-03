package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDirtWithRoots extends Block {
    BlockType<BlockDirtWithRoots> TYPE = BlockTypeBuilder
            .builder(BlockDirtWithRoots.class)
            .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS, true)
            .addBasicComponents()
            .build();
}
