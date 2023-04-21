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
public interface BlockHopper extends Block {
    BlockType<BlockHopper> TYPE = BlockTypeBuilder
            .builder(BlockHopper.class)
            .vanillaBlock(VanillaBlockId.HOPPER)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.TOGGLE_BIT)
            .build();
}
