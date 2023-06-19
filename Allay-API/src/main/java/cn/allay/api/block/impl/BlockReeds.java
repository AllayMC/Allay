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
public interface BlockReeds extends Block {
  BlockType<BlockReeds> TYPE = BlockTypeBuilder
          .builder(BlockReeds.class)
          .vanillaBlock(VanillaBlockId.REEDS, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();
}
