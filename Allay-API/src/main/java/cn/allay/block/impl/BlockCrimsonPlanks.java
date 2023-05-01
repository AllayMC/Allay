package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonPlanks extends Block {
    BlockType<BlockCrimsonPlanks> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPlanks.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
