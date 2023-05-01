package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWater extends Block {
    BlockType<BlockWater> TYPE = BlockTypeBuilder
            .builder(BlockWater.class)
            .vanillaBlock(VanillaBlockId.WATER, true)
            .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
