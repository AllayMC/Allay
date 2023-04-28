package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement116 extends Block {
    BlockType<BlockElement116> TYPE = BlockTypeBuilder
            .builder(BlockElement116.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_116, true)
            .addBasicComponents()
            .build();
}
