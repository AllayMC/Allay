package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPlanks extends Block {
    BlockType<BlockPlanks> TYPE = BlockTypeBuilder
            .builder(BlockPlanks.class)
            .vanillaBlock(VanillaBlockId.PLANKS, true)
            .withProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
            .addBasicComponents()
            .build();
}
