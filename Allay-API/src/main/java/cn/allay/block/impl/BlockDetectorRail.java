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
public interface BlockDetectorRail extends Block {
    BlockType<BlockDetectorRail> TYPE = BlockTypeBuilder
            .builder(BlockDetectorRail.class)
            .vanillaBlock(VanillaBlockId.DETECTOR_RAIL)
            .property(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .build();
}
