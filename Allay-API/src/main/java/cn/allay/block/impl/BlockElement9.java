package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement9 extends Block {
    BlockType<BlockElement9> TYPE = BlockTypeBuilder
            .builder(BlockElement9.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_9, true)
            .addBasicComponents()
            .build();
}
