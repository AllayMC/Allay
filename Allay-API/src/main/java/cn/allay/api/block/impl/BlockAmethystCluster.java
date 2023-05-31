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
public interface BlockAmethystCluster extends Block {
    BlockType<BlockAmethystCluster> TYPE = BlockTypeBuilder
            .builder(BlockAmethystCluster.class)
            .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER, true)
            .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
