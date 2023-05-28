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
public interface BlockWaxedExposedCutCopper extends Block {
    BlockType<BlockWaxedExposedCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
