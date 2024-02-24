package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWoodenPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWoodenPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.WOODEN_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockWoodenPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.WOODEN_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
