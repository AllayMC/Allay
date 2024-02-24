package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBambooPressurePlateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooPressurePlateBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockBambooPressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
  }
}
