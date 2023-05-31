package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndPortal extends Block {
    BlockType<BlockEndPortal> TYPE = BlockTypeBuilder
            .builder(BlockEndPortal.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL, true)
            .addBasicComponents()
            .build();
}
