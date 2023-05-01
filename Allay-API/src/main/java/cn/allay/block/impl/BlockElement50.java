package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement50 extends Block {
    BlockType<BlockElement50> TYPE = BlockTypeBuilder
            .builder(BlockElement50.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_50, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
