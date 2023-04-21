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
public interface BlockSeaPickle extends Block {
    BlockType<BlockSeaPickle> TYPE = BlockTypeBuilder
            .builder(BlockSeaPickle.class)
            .vanillaBlock(VanillaBlockId.SEA_PICKLE)
            .property(VanillaBlockPropertyTypes.CLUSTER_COUNT,
                    VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
}
