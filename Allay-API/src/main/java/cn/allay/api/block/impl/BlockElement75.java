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
public interface BlockElement75 extends Block {
    BlockType<BlockElement75> TYPE = BlockTypeBuilder
            .builder(BlockElement75.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_75, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
