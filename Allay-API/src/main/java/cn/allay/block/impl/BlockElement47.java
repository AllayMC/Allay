package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement47 extends Block {
    BlockType<BlockElement47> TYPE = BlockTypeBuilder
            .builder(BlockElement47.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_47)
            .build();
}
