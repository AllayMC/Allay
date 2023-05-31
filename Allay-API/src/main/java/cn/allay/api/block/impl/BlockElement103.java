package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement103 extends Block {
    BlockType<BlockElement103> TYPE = BlockTypeBuilder
            .builder(BlockElement103.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_103, true)
            .addBasicComponents()
            .build();
}
