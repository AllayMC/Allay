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
public interface BlockCrimsonHyphae extends Block {
    BlockType<BlockCrimsonHyphae> TYPE = BlockTypeBuilder
            .builder(BlockCrimsonHyphae.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
