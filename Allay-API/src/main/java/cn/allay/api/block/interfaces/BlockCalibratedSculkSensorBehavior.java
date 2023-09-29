package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCalibratedSculkSensorBehavior extends BlockBehavior {
  BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .build();
}
