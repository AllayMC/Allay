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
public interface BlockWaxedWeatheredCutCopperSlab extends Block {
    BlockType<BlockWaxedWeatheredCutCopperSlab> TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperSlab.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
