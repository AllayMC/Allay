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
public interface BlockPumpkin extends Block {
  BlockType<BlockPumpkin> TYPE = BlockTypeBuilder
          .builder(BlockPumpkin.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();
}
