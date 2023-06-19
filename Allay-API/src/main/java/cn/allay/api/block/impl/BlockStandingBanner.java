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
public interface BlockStandingBanner extends Block {
  BlockType<BlockStandingBanner> TYPE = BlockTypeBuilder
          .builder(BlockStandingBanner.class)
          .vanillaBlock(VanillaBlockId.STANDING_BANNER, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();
}
