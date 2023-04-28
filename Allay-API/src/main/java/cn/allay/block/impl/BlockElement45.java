package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement45 extends Block {
    BlockType<BlockElement45> TYPE = BlockTypeBuilder
            .builder(BlockElement45.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_45, true)
            .addBasicComponents()
            .build();
}
