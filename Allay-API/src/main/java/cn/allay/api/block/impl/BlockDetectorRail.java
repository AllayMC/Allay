package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDetectorRail extends Block {
    BlockType<BlockDetectorRail> TYPE = BlockTypeBuilder
            .builder(BlockDetectorRail.class)
            .vanillaBlock(VanillaBlockId.DETECTOR_RAIL, true)
            .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
