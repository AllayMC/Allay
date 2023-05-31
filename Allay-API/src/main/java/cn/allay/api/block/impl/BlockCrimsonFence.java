package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFence extends Block {
    BlockType<BlockCrimsonFence> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFence.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE, true)
            .addBasicComponents()
            .build();
}
