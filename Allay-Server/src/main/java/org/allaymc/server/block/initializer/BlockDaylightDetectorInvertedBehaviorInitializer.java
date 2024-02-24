package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDaylightDetectorInvertedBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDaylightDetectorInvertedBehaviorInitializer {
  static void init() {
    BlockTypes.DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
            .builder(BlockDaylightDetectorInvertedBehavior.class)
            .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
