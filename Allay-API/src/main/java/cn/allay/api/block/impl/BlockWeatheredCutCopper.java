package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCutCopper extends Block {
    BlockType<BlockWeatheredCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopper.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER, true)
            .addBasicComponents()
            .build();
}
