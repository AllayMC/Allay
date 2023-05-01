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
public interface BlockElement2 extends Block {
    BlockType<BlockElement2> TYPE = BlockTypeBuilder
            .builder(BlockElement2.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_2, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
