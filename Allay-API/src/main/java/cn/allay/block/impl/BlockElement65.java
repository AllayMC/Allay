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
public interface BlockElement65 extends Block {
    BlockType<BlockElement65> TYPE = BlockTypeBuilder
            .builder(BlockElement65.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_65, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
