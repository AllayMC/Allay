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
public interface BlockBrewingStand extends Block {
    BlockType<BlockBrewingStand> TYPE = BlockTypeBuilder
            .builder(BlockBrewingStand.class)
            .vanillaBlock(VanillaBlockId.BREWING_STAND)
            .property(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT,
                    VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT,
                    VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
            .build();
}
