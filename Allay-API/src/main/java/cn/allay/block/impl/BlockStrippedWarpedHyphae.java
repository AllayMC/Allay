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
public interface BlockStrippedWarpedHyphae extends Block {
    BlockType<BlockStrippedWarpedHyphae> TYPE = BlockTypeBuilder
            .builder(BlockStrippedWarpedHyphae.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE)
            .property(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
