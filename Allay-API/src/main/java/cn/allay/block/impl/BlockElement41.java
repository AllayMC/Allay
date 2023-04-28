package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement41 extends Block {
    BlockType<BlockElement41> TYPE = BlockTypeBuilder
            .builder(BlockElement41.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_41, true)
            .addBasicComponents()
            .build();
}
