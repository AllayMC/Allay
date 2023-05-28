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
public interface BlockGoldenRail extends Block {
    BlockType<BlockGoldenRail> TYPE = BlockTypeBuilder
            .builder(BlockGoldenRail.class)
            .vanillaBlock(VanillaBlockId.GOLDEN_RAIL, true)
            .withProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT,
                    VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
