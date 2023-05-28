package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSprucePressurePlate extends Block {
    BlockType<BlockSprucePressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockSprucePressurePlate.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
