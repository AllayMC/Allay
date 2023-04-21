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
public interface BlockPinkGlazedTerracotta extends Block {
    BlockType<BlockPinkGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockPinkGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.PINK_GLAZED_TERRACOTTA)
            .property(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
