package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockJunglePressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJunglePressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockJunglePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
