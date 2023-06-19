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
public interface BlockWheat extends Block {
  BlockType<BlockWheat> TYPE = BlockTypeBuilder
          .builder(BlockWheat.class)
          .vanillaBlock(VanillaBlockId.WHEAT, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();
}
