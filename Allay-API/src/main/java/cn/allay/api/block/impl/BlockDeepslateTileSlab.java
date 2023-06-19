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
public interface BlockDeepslateTileSlab extends Block {
  BlockType<BlockDeepslateTileSlab> TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileSlab.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();
}
