package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopper extends Block {
    BlockType<BlockWeatheredCopper> TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopper.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER, true)
            .addBasicComponents()
            .build();
}
