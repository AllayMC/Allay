package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothStone extends Block {
    BlockType<BlockSmoothStone> TYPE = BlockTypeBuilder
            .builder(BlockSmoothStone.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
            .build();
}
