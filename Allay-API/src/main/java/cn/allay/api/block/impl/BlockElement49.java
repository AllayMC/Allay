package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement49 extends Block {
    BlockType<BlockElement49> TYPE = BlockTypeBuilder
            .builder(BlockElement49.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_49, true)
            .addBasicComponents()
            .build();
}
