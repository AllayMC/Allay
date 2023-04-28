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
public interface BlockGoldenRail extends Block {
    BlockType<BlockGoldenRail> TYPE = BlockTypeBuilder
            .builder(BlockGoldenRail.class)
            .vanillaBlock(VanillaBlockId.GOLDEN_RAIL, true)
            .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .addBasicComponents()
            .build();
}
