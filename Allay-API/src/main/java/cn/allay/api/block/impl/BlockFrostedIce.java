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
public interface BlockFrostedIce extends Block {
    BlockType<BlockFrostedIce> TYPE = BlockTypeBuilder
            .builder(BlockFrostedIce.class)
            .vanillaBlock(VanillaBlockId.FROSTED_ICE, true)
            .withProperties(VanillaBlockPropertyTypes.AGE)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
