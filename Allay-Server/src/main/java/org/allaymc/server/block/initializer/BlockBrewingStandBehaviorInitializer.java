package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrewingStandBehaviorInitializer {
  static void init() {
    BlockTypes.BREWING_STAND_TYPE = BlockTypeBuilder
            .builder(BlockBrewingStandBehavior.class)
            .vanillaBlock(VanillaBlockId.BREWING_STAND)
            .setProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
            .build();
  }
}
