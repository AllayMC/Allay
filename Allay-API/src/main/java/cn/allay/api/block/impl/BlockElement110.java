package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement110 extends Block {
    BlockType<BlockElement110> TYPE = BlockTypeBuilder
            .builder(BlockElement110.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_110, true)
            .addBasicComponents()
            .build();
}
