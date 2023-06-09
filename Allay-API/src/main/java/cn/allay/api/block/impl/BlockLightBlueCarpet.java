package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCarpet extends Block {
  BlockType<BlockLightBlueCarpet> TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCarpet.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET, true)
          .addBasicComponents()
          .build();
}
