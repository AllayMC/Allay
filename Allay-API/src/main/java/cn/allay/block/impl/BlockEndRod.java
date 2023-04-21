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
public interface BlockEndRod extends Block {
    BlockType<BlockEndRod> TYPE = BlockTypeBuilder
            .builder(BlockEndRod.class)
            .vanillaBlock(VanillaBlockId.END_ROD)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
