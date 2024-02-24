package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCalibratedSculkSensorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCalibratedSculkSensorBehaviorInitializer {
  static void init() {
    BlockTypes.CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
            .builder(BlockCalibratedSculkSensorBehavior.class)
            .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
            .build();
  }
}
