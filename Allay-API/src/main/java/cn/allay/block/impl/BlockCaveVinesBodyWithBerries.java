package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

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
            .build().register(BlockTypeRegistry.getRegistry());
}
