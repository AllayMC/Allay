package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBirchPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockBirchPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
