package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDarkOakPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
