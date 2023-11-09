package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCalibratedSculkSensorBehavior extends BlockBehavior {
  BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensorBehavior.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .build();
}
