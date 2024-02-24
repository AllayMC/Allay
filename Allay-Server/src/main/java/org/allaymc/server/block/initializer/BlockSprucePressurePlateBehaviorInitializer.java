package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSprucePressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSprucePressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockSprucePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
