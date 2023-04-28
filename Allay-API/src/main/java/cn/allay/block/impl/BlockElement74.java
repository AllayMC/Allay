package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement74 extends Block {
    BlockType<BlockElement74> TYPE = BlockTypeBuilder
            .builder(BlockElement74.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_74, true)
            .addBasicComponents()
            .build();
}
