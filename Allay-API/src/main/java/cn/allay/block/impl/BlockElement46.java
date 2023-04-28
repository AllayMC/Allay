package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement46 extends Block {
    BlockType<BlockElement46> TYPE = BlockTypeBuilder
            .builder(BlockElement46.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_46, true)
            .addBasicComponents()
            .build();
}
