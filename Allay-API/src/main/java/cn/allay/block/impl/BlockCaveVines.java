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
public interface BlockCaveVines extends Block {
    BlockType<BlockCaveVines> TYPE = BlockTypeBuilder
            .builder(BlockCaveVines.class)
            .vanillaBlock(VanillaBlockId.CAVE_VINES)
            .property(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
            .build();
}
