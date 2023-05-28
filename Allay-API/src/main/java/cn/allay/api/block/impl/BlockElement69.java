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
public interface BlockElement69 extends Block {
    BlockType<BlockElement69> TYPE = BlockTypeBuilder
            .builder(BlockElement69.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_69, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
