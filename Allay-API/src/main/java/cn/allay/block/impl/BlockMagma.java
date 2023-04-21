package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagma extends Block {
    BlockType<BlockMagma> TYPE = BlockTypeBuilder
            .builder(BlockMagma.class)
            .vanillaBlock(VanillaBlockId.MAGMA)
            .build();
}
