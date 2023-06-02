package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchFence extends Block {
    BlockType<BlockBirchFence> TYPE = BlockTypeBuilder
            .builder(BlockBirchFence.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE, true)
            .addBasicComponents()
            .build();
}
