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
public interface BlockCherryPressurePlate extends Block {
    BlockType<BlockCherryPressurePlate> TYPE = BlockTypeBuilder
            .builder(BlockCherryPressurePlate.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE)
            .property(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
