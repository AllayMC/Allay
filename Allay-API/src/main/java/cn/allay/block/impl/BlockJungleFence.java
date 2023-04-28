package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJungleFence extends Block {
    BlockType<BlockJungleFence> TYPE = BlockTypeBuilder
            .builder(BlockJungleFence.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_FENCE)
            .build();
}
