package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement45 extends Block {
    BlockType<BlockElement45> TYPE = BlockTypeBuilder
            .builder(BlockElement45.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_45, true)
            .addBasicComponents()
            .build();
}
