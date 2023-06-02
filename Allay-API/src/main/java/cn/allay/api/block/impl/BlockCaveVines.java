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
public interface BlockCaveVines extends Block {
    BlockType<BlockCaveVines> TYPE = BlockTypeBuilder
            .builder(BlockCaveVines.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .addBasicComponents()
            .build();
}
