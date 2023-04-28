package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaFence extends Block {
    BlockType<BlockAcaciaFence> TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFence.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE)
            .build();
}
