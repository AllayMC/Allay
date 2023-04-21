package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBarrier extends Block {
    BlockType<BlockBarrier> TYPE = BlockTypeBuilder
            .builder(BlockBarrier.class)
            .vanillaBlock(VanillaBlockId.BARRIER)
            .build();
}
