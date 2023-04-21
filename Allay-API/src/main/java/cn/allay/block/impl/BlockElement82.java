package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement82 extends Block {
    BlockType<BlockElement82> TYPE = BlockTypeBuilder
            .builder(BlockElement82.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_82)
            .build();
}
