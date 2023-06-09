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
public interface BlockRedFlower extends Block {
  BlockType<BlockRedFlower> TYPE = BlockTypeBuilder
          .builder(BlockRedFlower.class)
          .vanillaBlock(VanillaBlockId.RED_FLOWER, true)
          .withProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
          .addBasicComponents()
          .build();
}
