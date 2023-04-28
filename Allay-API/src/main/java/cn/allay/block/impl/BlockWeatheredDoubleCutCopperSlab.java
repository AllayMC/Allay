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
public interface BlockWeatheredDoubleCutCopperSlab extends Block {
    BlockType<BlockWeatheredDoubleCutCopperSlab> TYPE = BlockTypeBuilder
            .builder(BlockWeatheredDoubleCutCopperSlab.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB, true)
            .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .addBasicComponents()
            .build();
}
