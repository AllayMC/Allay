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
public interface BlockSandstone extends Block {
    BlockType<BlockSandstone> TYPE = BlockTypeBuilder
            .builder(BlockSandstone.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE, true)
            .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
