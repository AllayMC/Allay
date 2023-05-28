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
public interface BlockHopper extends Block {
    BlockType<BlockHopper> TYPE = BlockTypeBuilder
            .builder(BlockHopper.class)
            .vanillaBlock(VanillaBlockId.HOPPER, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION,
                    VanillaBlockPropertyTypes.TOGGLE_BIT)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
