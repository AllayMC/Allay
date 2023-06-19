package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayWool extends Block {
  BlockType<BlockLightGrayWool> TYPE = BlockTypeBuilder
          .builder(BlockLightGrayWool.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL, true)
          .addBasicComponents()
          .build();
}
