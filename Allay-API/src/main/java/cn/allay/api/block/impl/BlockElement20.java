package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement20 extends Block {
    BlockType<BlockElement20> TYPE = BlockTypeBuilder
            .builder(BlockElement20.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_20, true)
            .addBasicComponents()
            .build();
}
