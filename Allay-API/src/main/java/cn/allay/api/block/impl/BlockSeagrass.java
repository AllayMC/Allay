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
public interface BlockSeagrass extends Block {
    BlockType<BlockSeagrass> TYPE = BlockTypeBuilder
            .builder(BlockSeagrass.class)
            .vanillaBlock(VanillaBlockId.SEAGRASS, true)
            .withProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
            .addBasicComponents()
            .build();
}
