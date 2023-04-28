package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement99 extends Block {
    BlockType<BlockElement99> TYPE = BlockTypeBuilder
            .builder(BlockElement99.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_99, true)
            .addBasicComponents()
            .build();
}
