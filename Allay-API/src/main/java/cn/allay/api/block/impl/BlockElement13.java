package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement13 extends Block {
  BlockType<BlockElement13> TYPE = BlockTypeBuilder
          .builder(BlockElement13.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_13, true)
          .addBasicComponents()
          .build();
}
