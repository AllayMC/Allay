package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement62 extends Block {
    BlockType<BlockElement62> TYPE = BlockTypeBuilder
            .builder(BlockElement62.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_62, true)
            .addBasicComponents()
            .build();
}
