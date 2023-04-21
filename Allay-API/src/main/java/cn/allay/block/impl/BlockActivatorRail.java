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
public interface BlockActivatorRail extends Block {
    BlockType<BlockActivatorRail> TYPE = BlockTypeBuilder
            .builder(BlockActivatorRail.class)
            .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL)
            .property(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .build();
}
