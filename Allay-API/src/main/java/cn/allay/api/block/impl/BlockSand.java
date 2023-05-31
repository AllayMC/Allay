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
public interface BlockSand extends Block {
    BlockType<BlockSand> TYPE = BlockTypeBuilder
            .builder(BlockSand.class)
            .vanillaBlock(VanillaBlockId.SAND, true)
            .withProperties(VanillaBlockPropertyTypes.SAND_TYPE)
            .addBasicComponents()
            .build();
}
