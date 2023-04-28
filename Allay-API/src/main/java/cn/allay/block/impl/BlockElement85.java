package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement85 extends Block {
    BlockType<BlockElement85> TYPE = BlockTypeBuilder
            .builder(BlockElement85.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_85, true)
            .addBasicComponents()
            .build();
}
