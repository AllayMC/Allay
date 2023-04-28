package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopper extends Block {
    BlockType<BlockWaxedWeatheredCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER, true)
            .addBasicComponents()
            .build();
}
