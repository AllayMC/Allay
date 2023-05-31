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
public interface BlockWaxedDoubleCutCopperSlab extends Block {
    BlockType<BlockWaxedDoubleCutCopperSlab> TYPE = BlockTypeBuilder
            .builder(BlockWaxedDoubleCutCopperSlab.class)
            .vanillaBlock(VanillaBlockId.WAXED_DOUBLE_CUT_COPPER_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
