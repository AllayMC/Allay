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
public interface BlockGrayCandleCake extends Block {
  BlockType<BlockGrayCandleCake> TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleCake.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();
}
