package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHangingRoots extends Block {
    BlockType<BlockHangingRoots> TYPE = BlockTypeBuilder
            .builder(BlockHangingRoots.class)
            .vanillaBlock(VanillaBlockId.HANGING_ROOTS, true)
            .addBasicComponents()
            .build();
}
