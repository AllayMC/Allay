package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslate extends Block {
    BlockType<BlockPolishedDeepslate> TYPE = BlockTypeBuilder
            .builder(BlockPolishedDeepslate.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE, true)
            .addBasicComponents()
            .build();
}
