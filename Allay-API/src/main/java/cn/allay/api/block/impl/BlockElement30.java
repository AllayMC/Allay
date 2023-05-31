package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement30 extends Block {
    BlockType<BlockElement30> TYPE = BlockTypeBuilder
            .builder(BlockElement30.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_30, true)
            .addBasicComponents()
            .build();
}
