package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopper extends Block {
  BlockType<BlockWaxedWeatheredCopper> TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER, true)
          .addBasicComponents()
          .build();
}
