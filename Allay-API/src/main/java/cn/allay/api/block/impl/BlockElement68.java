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
public interface BlockElement68 extends Block {
    BlockType<BlockElement68> TYPE = BlockTypeBuilder
            .builder(BlockElement68.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_68, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
