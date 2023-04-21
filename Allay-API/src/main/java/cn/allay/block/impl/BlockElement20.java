package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement20 extends Block {
    BlockType<BlockElement20> TYPE = BlockTypeBuilder
            .builder(BlockElement20.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_20)
            .build();
}
