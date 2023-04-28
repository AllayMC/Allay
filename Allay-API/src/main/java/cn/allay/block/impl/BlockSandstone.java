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
public interface BlockSandstone extends Block {
    BlockType<BlockSandstone> TYPE = BlockTypeBuilder
            .builder(BlockSandstone.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE, true)
            .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
            .addBasicComponents()
            .build();
}
