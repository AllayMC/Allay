package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTubeCoral extends Block {
  BlockType<BlockTubeCoral> TYPE = BlockTypeBuilder
          .builder(BlockTubeCoral.class)
          .vanillaBlock(VanillaBlockId.TUBE_CORAL, true)
          .addBasicComponents()
          .build();
}
