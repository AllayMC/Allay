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
public interface BlockTallgrass extends Block {
    BlockType<BlockTallgrass> TYPE = BlockTypeBuilder
            .builder(BlockTallgrass.class)
            .vanillaBlock(VanillaBlockId.TALLGRASS, true)
            .withProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
            .addBasicComponents()
            .build();
}
