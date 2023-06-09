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
public interface BlockDoubleStoneBlockSlab4 extends Block {
  BlockType<BlockDoubleStoneBlockSlab4> TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab4.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
          .addBasicComponents()
          .build();
}
