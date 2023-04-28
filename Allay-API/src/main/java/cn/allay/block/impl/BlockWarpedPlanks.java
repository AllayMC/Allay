package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedPlanks extends Block {
    BlockType<BlockWarpedPlanks> TYPE = BlockTypeBuilder
            .builder(BlockWarpedPlanks.class)
            .vanillaBlock(VanillaBlockId.WARPED_PLANKS, true)
            .addBasicComponents()
            .build();
}
