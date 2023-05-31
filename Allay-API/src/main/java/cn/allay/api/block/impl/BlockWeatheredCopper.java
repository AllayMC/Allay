package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

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
