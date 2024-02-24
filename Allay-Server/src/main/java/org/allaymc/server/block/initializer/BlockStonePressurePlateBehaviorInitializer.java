package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStonePressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStonePressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.STONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockStonePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
