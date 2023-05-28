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
public interface BlockElement31 extends Block {
    BlockType<BlockElement31> TYPE = BlockTypeBuilder
            .builder(BlockElement31.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_31, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
