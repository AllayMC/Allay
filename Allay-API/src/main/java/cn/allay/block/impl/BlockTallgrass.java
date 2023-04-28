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
public interface BlockTallgrass extends Block {
    BlockType<BlockTallgrass> TYPE = BlockTypeBuilder
            .builder(BlockTallgrass.class)
            .vanillaBlock(VanillaBlockId.TALLGRASS, true)
            .withProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
            .addBasicComponents()
            .build();
}
