package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement24 extends Block {
    BlockType<BlockElement24> TYPE = BlockTypeBuilder
            .builder(BlockElement24.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_24, true)
            .addBasicComponents()
            .build();
}
