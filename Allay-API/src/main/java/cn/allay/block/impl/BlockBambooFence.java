package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooFence extends Block {
    BlockType<BlockBambooFence> TYPE = BlockTypeBuilder
            .builder(BlockBambooFence.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_FENCE, true)
            .addBasicComponents()
            .build();
}
