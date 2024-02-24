package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHeavyWeightedPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHeavyWeightedPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockHeavyWeightedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
