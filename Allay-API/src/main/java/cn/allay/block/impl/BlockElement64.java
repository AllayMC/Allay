package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement64 extends Block {
    BlockType<BlockElement64> TYPE = BlockTypeBuilder
            .builder(BlockElement64.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_64)
            .build();
}
