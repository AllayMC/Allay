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
public interface BlockElement109 extends Block {
    BlockType<BlockElement109> TYPE = BlockTypeBuilder
            .builder(BlockElement109.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_109, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
