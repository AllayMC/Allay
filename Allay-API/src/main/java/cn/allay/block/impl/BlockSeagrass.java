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
public interface BlockSeagrass extends Block {
    BlockType<BlockSeagrass> TYPE = BlockTypeBuilder
            .builder(BlockSeagrass.class)
            .vanillaBlock(VanillaBlockId.SEAGRASS)
            .property(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
            .build();
}
