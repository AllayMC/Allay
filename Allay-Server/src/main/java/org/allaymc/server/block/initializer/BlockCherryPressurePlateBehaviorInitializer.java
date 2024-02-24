package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCherryPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockCherryPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
