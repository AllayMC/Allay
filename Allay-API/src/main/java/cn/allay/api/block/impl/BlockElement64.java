package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement64 extends Block {
    BlockType<BlockElement64> TYPE = BlockTypeBuilder
            .builder(BlockElement64.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_64, true)
            .addBasicComponents()
            .build();
}
