package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRail extends Block {
    BlockType<BlockRail> TYPE = BlockTypeBuilder
            .builder(BlockRail.class)
            .vanillaBlock(VanillaBlockId.RAIL, true)
            .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
