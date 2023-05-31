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
public interface BlockWarpedStem extends Block {
    BlockType<BlockWarpedStem> TYPE = BlockTypeBuilder
            .builder(BlockWarpedStem.class)
            .vanillaBlock(VanillaBlockId.WARPED_STEM, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
