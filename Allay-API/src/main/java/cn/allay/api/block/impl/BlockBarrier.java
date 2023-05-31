package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBarrier extends Block {
    BlockType<BlockBarrier> TYPE = BlockTypeBuilder
            .builder(BlockBarrier.class)
            .vanillaBlock(VanillaBlockId.BARRIER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
