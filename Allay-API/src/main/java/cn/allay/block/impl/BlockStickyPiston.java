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
public interface BlockStickyPiston extends Block {
    BlockType<BlockStickyPiston> TYPE = BlockTypeBuilder
            .builder(BlockStickyPiston.class)
            .vanillaBlock(VanillaBlockId.STICKY_PISTON)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
