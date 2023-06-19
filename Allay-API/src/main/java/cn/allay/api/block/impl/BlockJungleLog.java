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
public interface BlockJungleLog extends Block {
  BlockType<BlockJungleLog> TYPE = BlockTypeBuilder
          .builder(BlockJungleLog.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();
}
