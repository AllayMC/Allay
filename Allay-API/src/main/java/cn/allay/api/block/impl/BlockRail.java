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
public interface BlockRail extends Block {
  BlockType<BlockRail> TYPE = BlockTypeBuilder
          .builder(BlockRail.class)
          .vanillaBlock(VanillaBlockId.RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
          .addBasicComponents()
          .build();
}
