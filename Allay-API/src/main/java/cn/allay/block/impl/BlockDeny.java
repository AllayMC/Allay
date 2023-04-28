package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeny extends Block {
    BlockType<BlockDeny> TYPE = BlockTypeBuilder
            .builder(BlockDeny.class)
            .vanillaBlock(VanillaBlockId.DENY, true)
            .addBasicComponents()
            .build();
}
