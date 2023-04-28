package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement112 extends Block {
    BlockType<BlockElement112> TYPE = BlockTypeBuilder
            .builder(BlockElement112.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_112, true)
            .addBasicComponents()
            .build();
}
