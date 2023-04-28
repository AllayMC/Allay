package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement30 extends Block {
    BlockType<BlockElement30> TYPE = BlockTypeBuilder
            .builder(BlockElement30.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_30, true)
            .addBasicComponents()
            .build();
}
