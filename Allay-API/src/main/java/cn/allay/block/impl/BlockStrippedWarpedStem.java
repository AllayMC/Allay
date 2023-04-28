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
public interface BlockStrippedWarpedStem extends Block {
    BlockType<BlockStrippedWarpedStem> TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedStem.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
