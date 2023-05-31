package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement87 extends Block {
    BlockType<BlockElement87> TYPE = BlockTypeBuilder
            .builder(BlockElement87.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_87, true)
            .addBasicComponents()
            .build();
}
