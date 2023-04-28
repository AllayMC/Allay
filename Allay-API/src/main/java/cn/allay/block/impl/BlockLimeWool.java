package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeWool extends Block {
    BlockType<BlockLimeWool> TYPE = BlockTypeBuilder
            .builder(BlockLimeWool.class)
            .vanillaBlock(VanillaBlockId.LIME_WOOL, true)
            .addBasicComponents()
            .build();
}
