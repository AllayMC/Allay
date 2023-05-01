package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCutCopper extends Block {
    BlockType<BlockWaxedWeatheredCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
