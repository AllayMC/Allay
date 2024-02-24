package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
