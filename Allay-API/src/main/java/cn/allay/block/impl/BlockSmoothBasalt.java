package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothBasalt extends Block {
    BlockType<BlockSmoothBasalt> TYPE = BlockTypeBuilder
            .builder(BlockSmoothBasalt.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_BASALT)
            .build();
}
