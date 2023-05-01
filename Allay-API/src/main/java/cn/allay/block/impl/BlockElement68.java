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
public interface BlockElement68 extends Block {
    BlockType<BlockElement68> TYPE = BlockTypeBuilder
            .builder(BlockElement68.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_68, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
