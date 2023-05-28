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
public interface BlockElement116 extends Block {
    BlockType<BlockElement116> TYPE = BlockTypeBuilder
            .builder(BlockElement116.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_116, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
