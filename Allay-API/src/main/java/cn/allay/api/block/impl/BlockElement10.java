package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement10 extends Block {
    BlockType<BlockElement10> TYPE = BlockTypeBuilder
            .builder(BlockElement10.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_10, true)
            .addBasicComponents()
            .build();
}
