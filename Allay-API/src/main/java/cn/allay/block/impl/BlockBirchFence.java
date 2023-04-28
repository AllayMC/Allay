package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchFence extends Block {
    BlockType<BlockBirchFence> TYPE = BlockTypeBuilder
            .builder(BlockBirchFence.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE)
            .build();
}
