package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFence extends Block {
    BlockType<BlockCrimsonFence> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFence.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
