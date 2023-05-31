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
public interface BlockDaylightDetector extends Block {
    BlockType<BlockDaylightDetector> TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetector.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build();
}
