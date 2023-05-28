package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

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
            .build().register(BlockTypeRegistry.getRegistry());
}
