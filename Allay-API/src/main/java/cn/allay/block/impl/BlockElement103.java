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
public interface BlockElement103 extends Block {
    BlockType<BlockElement103> TYPE = BlockTypeBuilder
            .builder(BlockElement103.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_103, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
