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
public interface BlockWeepingVines extends Block {
    BlockType<BlockWeepingVines> TYPE = BlockTypeBuilder
            .builder(BlockWeepingVines.class)
            .vanillaBlock(VanillaBlockId.WEEPING_VINES, true)
            .withProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
            .addBasicComponents()
            .build();
}
