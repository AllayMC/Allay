package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement18 extends Block {
    BlockType<BlockElement18> TYPE = BlockTypeBuilder
            .builder(BlockElement18.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_18, true)
            .addBasicComponents()
            .build();
}
