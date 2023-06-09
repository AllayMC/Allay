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
public interface BlockSculkShrieker extends Block {
  BlockType<BlockSculkShrieker> TYPE = BlockTypeBuilder
          .builder(BlockSculkShrieker.class)
          .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER, true)
          .withProperties(VanillaBlockPropertyTypes.CAN_SUMMON, VanillaBlockPropertyTypes.ACTIVE)
          .addBasicComponents()
          .build();
}
