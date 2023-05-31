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
public interface BlockCaveVinesBodyWithBerries extends Block {
    BlockType<BlockCaveVinesBodyWithBerries> TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesBodyWithBerries.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES, true)
            .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .addBasicComponents()
            .build();
}
