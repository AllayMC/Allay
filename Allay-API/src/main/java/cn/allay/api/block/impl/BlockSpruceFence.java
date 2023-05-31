package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSpruceFence extends Block {
    BlockType<BlockSpruceFence> TYPE = BlockTypeBuilder
            .builder(BlockSpruceFence.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_FENCE, true)
            .addBasicComponents()
            .build();
}
