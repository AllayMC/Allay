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
public interface BlockElement8 extends Block {
    BlockType<BlockElement8> TYPE = BlockTypeBuilder
            .builder(BlockElement8.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_8, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
