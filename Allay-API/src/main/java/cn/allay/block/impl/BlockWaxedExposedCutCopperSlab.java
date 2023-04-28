package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

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
