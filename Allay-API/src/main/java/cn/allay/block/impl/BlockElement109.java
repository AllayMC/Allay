package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement109 extends Block {
    BlockType<BlockElement109> TYPE = BlockTypeBuilder
            .builder(BlockElement109.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_109)
            .build();
}
