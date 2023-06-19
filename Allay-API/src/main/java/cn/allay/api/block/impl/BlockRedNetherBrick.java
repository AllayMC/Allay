package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedNetherBrick extends Block {
  BlockType<BlockRedNetherBrick> TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrick.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK, true)
          .addBasicComponents()
          .build();
}
