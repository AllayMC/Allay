package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangrovePlanks extends Block {
    BlockType<BlockMangrovePlanks> TYPE = BlockTypeBuilder
            .builder(BlockMangrovePlanks.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS, true)
            .addBasicComponents()
            .build();
}
