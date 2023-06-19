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
public interface BlockCalibratedSculkSensor extends Block {
  BlockType<BlockCalibratedSculkSensor> TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensor.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR, true)
          .withProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
