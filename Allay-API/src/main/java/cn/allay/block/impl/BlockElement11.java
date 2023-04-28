package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement11 extends Block {
    BlockType<BlockElement11> TYPE = BlockTypeBuilder
            .builder(BlockElement11.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_11, true)
            .addBasicComponents()
            .build();
}
