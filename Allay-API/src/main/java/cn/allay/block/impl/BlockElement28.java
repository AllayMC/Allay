package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement28 extends Block {
    BlockType<BlockElement28> TYPE = BlockTypeBuilder
            .builder(BlockElement28.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_28, true)
            .addBasicComponents()
            .build();
}
