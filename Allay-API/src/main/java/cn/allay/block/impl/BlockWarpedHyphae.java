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
public interface BlockWarpedHyphae extends Block {
    BlockType<BlockWarpedHyphae> TYPE = BlockTypeBuilder
            .builder(BlockWarpedHyphae.class)
            .vanillaBlock(VanillaBlockId.WARPED_HYPHAE, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
