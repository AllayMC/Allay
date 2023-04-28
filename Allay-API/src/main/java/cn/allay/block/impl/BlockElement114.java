package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement114 extends Block {
    BlockType<BlockElement114> TYPE = BlockTypeBuilder
            .builder(BlockElement114.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_114, true)
            .addBasicComponents()
            .build();
}
