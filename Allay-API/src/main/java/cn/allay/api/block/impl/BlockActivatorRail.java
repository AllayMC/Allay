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
public interface BlockActivatorRail extends Block {
    BlockType<BlockActivatorRail> TYPE = BlockTypeBuilder
            .builder(BlockActivatorRail.class)
            .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL, true)
            .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .addBasicComponents()
            .build();
}
