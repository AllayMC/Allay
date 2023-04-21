package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement5 extends Block {
    BlockType<BlockElement5> TYPE = BlockTypeBuilder
            .builder(BlockElement5.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_5)
            .build();
}
