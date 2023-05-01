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
public interface BlockElement48 extends Block {
    BlockType<BlockElement48> TYPE = BlockTypeBuilder
            .builder(BlockElement48.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_48, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
