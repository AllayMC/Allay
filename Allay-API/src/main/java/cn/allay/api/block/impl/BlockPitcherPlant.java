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
public interface BlockPitcherPlant extends Block {
  BlockType<BlockPitcherPlant> TYPE = BlockTypeBuilder
          .builder(BlockPitcherPlant.class)
          .vanillaBlock(VanillaBlockId.PITCHER_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();
}
