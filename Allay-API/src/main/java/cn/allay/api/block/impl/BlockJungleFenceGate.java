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
public interface BlockJungleFenceGate extends Block {
  BlockType<BlockJungleFenceGate> TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceGate.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
