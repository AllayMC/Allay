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
public interface BlockStrippedCrimsonHyphae extends Block {
    BlockType<BlockStrippedCrimsonHyphae> TYPE = BlockTypeBuilder
            .builder(BlockStrippedCrimsonHyphae.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
