package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryPlanks extends Block {
    BlockType<BlockCherryPlanks> TYPE = BlockTypeBuilder
            .builder(BlockCherryPlanks.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PLANKS, true)
            .addBasicComponents()
            .build();
}
