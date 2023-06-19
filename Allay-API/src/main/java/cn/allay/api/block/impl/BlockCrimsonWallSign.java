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
public interface BlockCrimsonWallSign extends Block {
  BlockType<BlockCrimsonWallSign> TYPE = BlockTypeBuilder
          .builder(BlockCrimsonWallSign.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();
}
