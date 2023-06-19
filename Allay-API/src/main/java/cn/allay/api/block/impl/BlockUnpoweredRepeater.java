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
public interface BlockUnpoweredRepeater extends Block {
  BlockType<BlockUnpoweredRepeater> TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredRepeater.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER, true)
          .withProperties(VanillaBlockPropertyTypes.REPEATER_DELAY, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
