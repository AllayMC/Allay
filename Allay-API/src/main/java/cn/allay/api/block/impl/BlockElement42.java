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
public interface BlockElement42 extends Block {
    BlockType<BlockElement42> TYPE = BlockTypeBuilder
            .builder(BlockElement42.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_42, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
