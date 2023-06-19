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
public interface BlockBigDripleaf extends Block {
  BlockType<BlockBigDripleaf> TYPE = BlockTypeBuilder
          .builder(BlockBigDripleaf.class)
          .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF, true)
          .withProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
