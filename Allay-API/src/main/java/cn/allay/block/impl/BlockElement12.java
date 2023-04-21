package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement12 extends Block {
    BlockType<BlockElement12> TYPE = BlockTypeBuilder
            .builder(BlockElement12.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_12)
            .build();
}
