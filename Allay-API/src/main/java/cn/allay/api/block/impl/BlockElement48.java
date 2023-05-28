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
public interface BlockElement48 extends Block {
    BlockType<BlockElement48> TYPE = BlockTypeBuilder
            .builder(BlockElement48.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_48, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
