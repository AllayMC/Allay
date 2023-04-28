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
public interface BlockCaveVinesHeadWithBerries extends Block {
    BlockType<BlockCaveVinesHeadWithBerries> TYPE = BlockTypeBuilder
            .builder(BlockCaveVinesHeadWithBerries.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES, true)
            .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .addBasicComponents()
            .build();
}
