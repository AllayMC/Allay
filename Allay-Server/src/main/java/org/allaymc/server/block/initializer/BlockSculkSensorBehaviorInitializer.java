package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSculkSensorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkSensorBehaviorInitializer {
  static void init() {
    BlockTypes.SCULK_SENSOR_TYPE = BlockTypeBuilder
            .builder(BlockSculkSensorBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_SENSOR)
            .setProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
            .build();
  }
}
