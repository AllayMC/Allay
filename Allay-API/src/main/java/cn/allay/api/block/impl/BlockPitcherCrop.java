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
public interface BlockPitcherCrop extends Block {
  BlockType<BlockPitcherCrop> TYPE = BlockTypeBuilder
          .builder(BlockPitcherCrop.class)
          .vanillaBlock(VanillaBlockId.PITCHER_CROP, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();
}
