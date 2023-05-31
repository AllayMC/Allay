package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDirt extends Block {
    BlockType<BlockDirt> TYPE = BlockTypeBuilder
            .builder(BlockDirt.class)
            .vanillaBlock(VanillaBlockId.DIRT, true)
            .withProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
