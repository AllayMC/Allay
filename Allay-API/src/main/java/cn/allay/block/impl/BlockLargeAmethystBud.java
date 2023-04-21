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
public interface BlockLargeAmethystBud extends Block {
    BlockType<BlockLargeAmethystBud> TYPE = BlockTypeBuilder
            .builder(BlockLargeAmethystBud.class)
            .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
