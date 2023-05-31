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
public interface BlockBlackGlazedTerracotta extends Block {
    BlockType<BlockBlackGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockBlackGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
