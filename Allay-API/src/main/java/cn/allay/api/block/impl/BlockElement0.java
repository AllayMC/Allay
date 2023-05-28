package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement0 extends Block {
    BlockType<BlockElement0> TYPE = BlockTypeBuilder
            .builder(BlockElement0.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_0, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
