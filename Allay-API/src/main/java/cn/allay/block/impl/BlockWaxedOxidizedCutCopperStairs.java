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
public interface BlockWaxedOxidizedCutCopperStairs extends Block {
    BlockType<BlockWaxedOxidizedCutCopperStairs> TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperStairs.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, true)
            .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT,
                    VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addBasicComponents()
            .build();
}
