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
public interface BlockPointedDripstone extends Block {
  BlockType<BlockPointedDripstone> TYPE = BlockTypeBuilder
          .builder(BlockPointedDripstone.class)
          .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();
}
