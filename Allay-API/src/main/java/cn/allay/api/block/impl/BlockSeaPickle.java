package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSeaPickle extends Block {
  BlockType<BlockSeaPickle> TYPE = BlockTypeBuilder
          .builder(BlockSeaPickle.class)
          .vanillaBlock(VanillaBlockId.SEA_PICKLE, true)
          .withProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();
}
