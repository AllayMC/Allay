package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedCutCopper extends Block {
    BlockType<BlockWaxedCutCopper> TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopper.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER, true)
            .addBasicComponents()
            .build();
}
