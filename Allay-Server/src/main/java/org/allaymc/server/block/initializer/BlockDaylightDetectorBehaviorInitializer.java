package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDaylightDetectorBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDaylightDetectorBehaviorInitializer {
  static void init() {
    BlockTypes.DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorBehavior.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
