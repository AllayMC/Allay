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
public interface BlockDarkOakPressurePlate extends Block {
    BlockType<BlockDarkOakPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockDarkOakPressurePlate.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
