package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStonebrick extends Block {
    BlockType<BlockStonebrick> TYPE = BlockTypeBuilder
            .builder(BlockStonebrick.class)
            .vanillaBlock(VanillaBlockId.STONEBRICK, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
