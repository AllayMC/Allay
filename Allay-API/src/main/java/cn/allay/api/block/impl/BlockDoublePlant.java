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
public interface BlockDoublePlant extends Block {
  BlockType<BlockDoublePlant> TYPE = BlockTypeBuilder
          .builder(BlockDoublePlant.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE)
          .addBasicComponents()
          .build();
}
