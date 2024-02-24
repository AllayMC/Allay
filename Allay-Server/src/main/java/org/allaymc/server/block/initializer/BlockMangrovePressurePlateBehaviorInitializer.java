package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMangrovePressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangrovePressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
