package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement88 extends Block {
    BlockType<BlockElement88> TYPE = BlockTypeBuilder
            .builder(BlockElement88.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_88)
            .build();
}
