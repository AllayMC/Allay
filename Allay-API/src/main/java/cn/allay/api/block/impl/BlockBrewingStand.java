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
public interface BlockBrewingStand extends Block {
    BlockType<BlockBrewingStand> TYPE = BlockTypeBuilder
            .builder(BlockBrewingStand.class)
            .vanillaBlock(VanillaBlockId.BREWING_STAND, true)
            .withProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT,
                    VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT,
                    VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
            .addBasicComponents()
            .build();
}
