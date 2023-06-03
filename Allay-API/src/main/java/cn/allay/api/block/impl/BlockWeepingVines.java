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
public interface BlockWeepingVines extends Block {
    BlockType<BlockWeepingVines> TYPE = BlockTypeBuilder
            .builder(BlockWeepingVines.class)
            .vanillaBlock(VanillaBlockId.WEEPING_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
            .addBasicComponents()
            .build();
}
