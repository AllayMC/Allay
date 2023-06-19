package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement1 extends Block {
  BlockType<BlockElement1> TYPE = BlockTypeBuilder
          .builder(BlockElement1.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_1, true)
          .addBasicComponents()
          .build();
}
