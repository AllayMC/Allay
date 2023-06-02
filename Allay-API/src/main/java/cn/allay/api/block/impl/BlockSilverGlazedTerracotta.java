package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSilverGlazedTerracotta extends Block {
    BlockType<BlockSilverGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockSilverGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
