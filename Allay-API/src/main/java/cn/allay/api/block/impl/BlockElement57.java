package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement57 extends Block {
    BlockType<BlockElement57> TYPE = BlockTypeBuilder
            .builder(BlockElement57.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_57, true)
            .addBasicComponents()
            .build();
}
