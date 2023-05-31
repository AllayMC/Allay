package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement27 extends Block {
    BlockType<BlockElement27> TYPE = BlockTypeBuilder
            .builder(BlockElement27.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_27, true)
            .addBasicComponents()
            .build();
}
