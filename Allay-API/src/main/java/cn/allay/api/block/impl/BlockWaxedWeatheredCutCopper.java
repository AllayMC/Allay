package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

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
