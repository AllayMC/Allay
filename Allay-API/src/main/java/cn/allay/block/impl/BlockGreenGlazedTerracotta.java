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
public interface BlockGreenGlazedTerracotta extends Block {
    BlockType<BlockGreenGlazedTerracotta> TYPE = BlockTypeBuilder
            .builder(BlockGreenGlazedTerracotta.class)
            .vanillaBlock(VanillaBlockId.GREEN_GLAZED_TERRACOTTA, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
