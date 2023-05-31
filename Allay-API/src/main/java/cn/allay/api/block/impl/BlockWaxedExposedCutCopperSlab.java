package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCutCopperSlab extends Block {
    BlockType<BlockWaxedExposedCutCopperSlab> TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperSlab.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
