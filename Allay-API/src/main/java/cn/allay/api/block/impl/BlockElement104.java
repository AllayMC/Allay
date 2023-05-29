package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement104 extends Block {
    BlockType<BlockElement104> TYPE = BlockTypeBuilder
            .builder(BlockElement104.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_104, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
