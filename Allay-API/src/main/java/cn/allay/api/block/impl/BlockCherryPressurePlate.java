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
public interface BlockCherryPressurePlate extends Block {
    BlockType<BlockCherryPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockCherryPressurePlate.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
