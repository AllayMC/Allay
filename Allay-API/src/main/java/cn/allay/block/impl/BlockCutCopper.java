package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCutCopper extends Block {
    BlockType<BlockCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockCutCopper.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER, true)
            .addBasicComponents()
            .build();
}
