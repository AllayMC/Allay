package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadHornCoral extends Block {
  BlockType<BlockDeadHornCoral> TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL, true)
          .addBasicComponents()
          .build();
}
