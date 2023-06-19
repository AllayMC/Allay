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
public interface BlockCherryHangingSign extends Block {
  BlockType<BlockCherryHangingSign> TYPE = BlockTypeBuilder
          .builder(BlockCherryHangingSign.class)
          .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();
}
