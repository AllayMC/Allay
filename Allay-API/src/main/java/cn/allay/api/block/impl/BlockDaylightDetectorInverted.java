package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;
import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDaylightDetectorInverted extends Block {
    BlockType<BlockDaylightDetectorInverted> TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorInverted.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED, true)
            .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
