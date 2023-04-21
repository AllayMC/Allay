package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackstone extends Block {
    BlockType<BlockBlackstone> TYPE = BlockTypeBuilder
            .builder(BlockBlackstone.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE)
            .build();
}
