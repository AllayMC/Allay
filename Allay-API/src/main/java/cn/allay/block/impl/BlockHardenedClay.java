package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardenedClay extends Block {
    BlockType<BlockHardenedClay> TYPE = BlockTypeBuilder
            .builder(BlockHardenedClay.class)
            .vanillaBlock(VanillaBlockId.HARDENED_CLAY, true)
            .addBasicComponents()
            .build();
}
