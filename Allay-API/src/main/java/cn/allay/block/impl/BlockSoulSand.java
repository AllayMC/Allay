package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulSand extends Block {
    BlockType<BlockSoulSand> TYPE = BlockTypeBuilder
            .builder(BlockSoulSand.class)
            .vanillaBlock(VanillaBlockId.SOUL_SAND)
            .build();
}
