package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopper extends Block {
    BlockType<BlockWaxedCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER, true)
            .addBasicComponents()
            .build();
}
