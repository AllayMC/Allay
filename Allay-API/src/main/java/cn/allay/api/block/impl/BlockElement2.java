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
public interface BlockElement2 extends Block {
    BlockType<BlockElement2> TYPE = BlockTypeBuilder
            .builder(BlockElement2.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_2, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
