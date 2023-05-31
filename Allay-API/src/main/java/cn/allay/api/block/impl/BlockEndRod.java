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
public interface BlockEndRod extends Block {
    BlockType<BlockEndRod> TYPE = BlockTypeBuilder
            .builder(BlockEndRod.class)
            .vanillaBlock(VanillaBlockId.END_ROD, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
