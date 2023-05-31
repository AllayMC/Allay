package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement115 extends Block {
    BlockType<BlockElement115> TYPE = BlockTypeBuilder
            .builder(BlockElement115.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_115, true)
            .addBasicComponents()
            .build();
}
