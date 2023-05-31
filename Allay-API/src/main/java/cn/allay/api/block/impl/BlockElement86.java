package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement86 extends Block {
    BlockType<BlockElement86> TYPE = BlockTypeBuilder
            .builder(BlockElement86.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_86, true)
            .addBasicComponents()
            .build();
}
