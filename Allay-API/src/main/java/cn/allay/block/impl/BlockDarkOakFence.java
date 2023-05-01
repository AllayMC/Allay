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
public interface BlockDarkOakFence extends Block {
    BlockType<BlockDarkOakFence> TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFence.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
