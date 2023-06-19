package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadBubbleCoral extends Block {
  BlockType<BlockDeadBubbleCoral> TYPE = BlockTypeBuilder
          .builder(BlockDeadBubbleCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL, true)
          .addBasicComponents()
          .build();
}
