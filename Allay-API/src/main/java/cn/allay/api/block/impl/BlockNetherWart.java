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
public interface BlockNetherWart extends Block {
  BlockType<BlockNetherWart> TYPE = BlockTypeBuilder
          .builder(BlockNetherWart.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();
}
