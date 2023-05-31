package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement4 extends Block {
    BlockType<BlockElement4> TYPE = BlockTypeBuilder
            .builder(BlockElement4.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_4, true)
            .addBasicComponents()
            .build();
}
