package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockClientRequestPlaceholderBlock extends Block {
    BlockType<BlockClientRequestPlaceholderBlock> TYPE = BlockTypeBuilder
            .builder(BlockClientRequestPlaceholderBlock.class)
            .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK, true)
            .addBasicComponents()
            .build();
}
