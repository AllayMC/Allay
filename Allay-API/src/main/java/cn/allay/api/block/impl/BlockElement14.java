package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement14 extends Block {
  BlockType<BlockElement14> TYPE = BlockTypeBuilder
          .builder(BlockElement14.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_14, true)
          .addBasicComponents()
          .build();
}
