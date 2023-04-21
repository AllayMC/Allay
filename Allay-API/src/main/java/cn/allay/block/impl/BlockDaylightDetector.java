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
public interface BlockDaylightDetector extends Block {
    BlockType<BlockDaylightDetector> TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetector.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
            .property(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
