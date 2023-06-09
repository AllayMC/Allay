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
public interface BlockBambooSapling extends Block {
  BlockType<BlockBambooSapling> TYPE = BlockTypeBuilder
          .builder(BlockBambooSapling.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.SAPLING_TYPE, VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();
}
