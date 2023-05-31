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
public interface BlockLargeAmethystBud extends Block {
    BlockType<BlockLargeAmethystBud> TYPE = BlockTypeBuilder
            .builder(BlockLargeAmethystBud.class)
            .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
