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
public interface BlockPolishedBlackstoneBrickStairs extends Block {
  BlockType<BlockPolishedBlackstoneBrickStairs> TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();
}
