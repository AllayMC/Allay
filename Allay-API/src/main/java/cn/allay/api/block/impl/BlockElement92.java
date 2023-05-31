package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement92 extends Block {
    BlockType<BlockElement92> TYPE = BlockTypeBuilder
            .builder(BlockElement92.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_92, true)
            .addBasicComponents()
            .build();
}
