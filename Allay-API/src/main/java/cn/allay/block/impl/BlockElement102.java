package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement102 extends Block {
    BlockType<BlockElement102> TYPE = BlockTypeBuilder
            .builder(BlockElement102.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_102)
            .build();
}
