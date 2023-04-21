package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement117 extends Block {
    BlockType<BlockElement117> TYPE = BlockTypeBuilder
            .builder(BlockElement117.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_117)
            .build();
}
