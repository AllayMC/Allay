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
public interface BlockCoralFanHang3 extends Block {
  BlockType<BlockCoralFanHang3> TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang3.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();
}
