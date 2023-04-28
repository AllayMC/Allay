package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryFence extends Block {
    BlockType<BlockCherryFence> TYPE = BlockTypeBuilder
            .builder(BlockCherryFence.class)
            .vanillaBlock(VanillaBlockId.CHERRY_FENCE, true)
            .addBasicComponents()
            .build();
}
