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
public interface BlockLectern extends Block {
  BlockType<BlockLectern> TYPE = BlockTypeBuilder
          .builder(BlockLectern.class)
          .vanillaBlock(VanillaBlockId.LECTERN, true)
          .withProperties(VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
