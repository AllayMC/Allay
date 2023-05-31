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
public interface BlockRedGlazedTerracotta extends Block {
    BlockType<BlockRedGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockRedGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.RED_GLAZED_TERRACOTTA, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
