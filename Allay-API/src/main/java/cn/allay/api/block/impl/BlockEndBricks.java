package cn.allay.api.block.impl;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndBricks extends Block {
    BlockType<BlockEndBricks> TYPE = BlockTypeBuilder
            .builder(BlockEndBricks.class)
            .vanillaBlock(VanillaBlockId.END_BRICKS, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
