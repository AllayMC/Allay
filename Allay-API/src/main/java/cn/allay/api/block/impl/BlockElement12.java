package cn.allay.api.block.impl;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement12 extends Block {
    BlockType<BlockElement12> TYPE = BlockTypeBuilder
            .builder(BlockElement12.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_12, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
