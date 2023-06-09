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
public interface BlockMangrovePressurePlate extends Block {
  BlockType<BlockMangrovePressurePlate> TYPE = BlockTypeBuilder
          .builder(BlockMangrovePressurePlate.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();
}
