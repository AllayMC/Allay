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
public interface BlockPolishedBasalt extends Block {
    BlockType<BlockPolishedBasalt> TYPE = BlockTypeBuilder
            .builder(BlockPolishedBasalt.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BASALT, true)
            .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .addBasicComponents()
            .build();
}
