package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooPressurePlate extends Block {
    BlockType<BlockBambooPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockBambooPressurePlate.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
