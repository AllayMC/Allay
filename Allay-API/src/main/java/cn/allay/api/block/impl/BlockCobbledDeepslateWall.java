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
public interface BlockCobbledDeepslateWall extends Block {
  BlockType<BlockCobbledDeepslateWall> TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateWall.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();
}
