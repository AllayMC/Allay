package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLightWeightedPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightWeightedPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockLightWeightedPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
