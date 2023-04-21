package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement36 extends Block {
    BlockType<BlockElement36> TYPE = BlockTypeBuilder
            .builder(BlockElement36.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_36)
            .build();
}
