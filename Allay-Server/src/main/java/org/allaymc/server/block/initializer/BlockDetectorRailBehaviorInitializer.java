package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDetectorRailBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDetectorRailBehaviorInitializer {
  static void init() {
    BlockTypes.DETECTOR_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockDetectorRailBehavior.class)
            .vanillaBlock(VanillaBlockId.DETECTOR_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
  }
}
