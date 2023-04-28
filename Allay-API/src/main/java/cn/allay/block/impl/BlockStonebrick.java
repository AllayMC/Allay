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
public interface BlockStonebrick extends Block {
    BlockType<BlockStonebrick> TYPE = BlockTypeBuilder
            .builder(BlockStonebrick.class)
            .vanillaBlock(VanillaBlockId.STONEBRICK, true)
            .withProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
            .addBasicComponents()
            .build();
}
