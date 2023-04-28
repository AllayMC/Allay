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
public interface BlockLimeGlazedTerracotta extends Block {
    BlockType<BlockLimeGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockLimeGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
