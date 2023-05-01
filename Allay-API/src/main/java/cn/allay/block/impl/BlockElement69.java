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
public interface BlockElement69 extends Block {
    BlockType<BlockElement69> TYPE = BlockTypeBuilder
            .builder(BlockElement69.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_69, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
