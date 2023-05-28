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
public interface BlockElement47 extends Block {
    BlockType<BlockElement47> TYPE = BlockTypeBuilder
            .builder(BlockElement47.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_47, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
