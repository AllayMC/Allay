package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement54 extends Block {
    BlockType<BlockElement54> TYPE = BlockTypeBuilder
            .builder(BlockElement54.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_54, true)
            .addBasicComponents()
            .build();
}
