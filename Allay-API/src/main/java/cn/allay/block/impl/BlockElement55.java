package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement55 extends Block {
    BlockType<BlockElement55> TYPE = BlockTypeBuilder
            .builder(BlockElement55.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_55, true)
            .addBasicComponents()
            .build();
}
