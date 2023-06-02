package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCaveVinesHeadWithBerries extends Block {
    BlockType<BlockCaveVinesHeadWithBerries> TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesHeadWithBerries.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES, true)
            .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .addBasicComponents()
            .build();
}
