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
public interface BlockLightWeightedPressurePlate extends Block {
    BlockType<BlockLightWeightedPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockLightWeightedPressurePlate.class)
            .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
