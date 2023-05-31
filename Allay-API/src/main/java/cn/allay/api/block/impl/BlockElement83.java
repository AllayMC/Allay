package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement83 extends Block {
    BlockType<BlockElement83> TYPE = BlockTypeBuilder
            .builder(BlockElement83.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_83, true)
            .addBasicComponents()
            .build();
}
