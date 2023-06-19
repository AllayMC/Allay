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
public interface BlockCauldron extends Block {
  BlockType<BlockCauldron> TYPE = BlockTypeBuilder
          .builder(BlockCauldron.class)
          .vanillaBlock(VanillaBlockId.CAULDRON, true)
          .withProperties(VanillaBlockPropertyTypes.FILL_LEVEL, VanillaBlockPropertyTypes.CAULDRON_LIQUID)
          .addBasicComponents()
          .build();
}
