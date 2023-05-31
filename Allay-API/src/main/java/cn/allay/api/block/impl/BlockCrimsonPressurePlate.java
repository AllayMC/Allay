package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonPressurePlate extends Block {
    BlockType<BlockCrimsonPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPressurePlate.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
