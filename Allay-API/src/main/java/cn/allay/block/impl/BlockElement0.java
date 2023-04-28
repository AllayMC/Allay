package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement0 extends Block {
    BlockType<BlockElement0> TYPE = BlockTypeBuilder
            .builder(BlockElement0.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_0, true)
            .addBasicComponents()
            .build();
}
