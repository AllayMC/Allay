package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
