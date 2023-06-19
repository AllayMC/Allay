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
public interface BlockMangroveLeaves extends Block {
  BlockType<BlockMangroveLeaves> TYPE = BlockTypeBuilder
          .builder(BlockMangroveLeaves.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();
}
