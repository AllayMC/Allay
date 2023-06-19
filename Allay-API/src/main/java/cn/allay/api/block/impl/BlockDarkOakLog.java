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
public interface BlockDarkOakLog extends Block {
  BlockType<BlockDarkOakLog> TYPE = BlockTypeBuilder
          .builder(BlockDarkOakLog.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();
}
