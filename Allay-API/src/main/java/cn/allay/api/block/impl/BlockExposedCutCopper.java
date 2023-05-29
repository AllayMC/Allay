package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCutCopper extends Block {
    BlockType<BlockExposedCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopper.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER, true)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
