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
public interface BlockElement57 extends Block {
    BlockType<BlockElement57> TYPE = BlockTypeBuilder
            .builder(BlockElement57.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_57, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
