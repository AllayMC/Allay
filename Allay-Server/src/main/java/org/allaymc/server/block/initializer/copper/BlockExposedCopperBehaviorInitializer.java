package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockExposedCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER)
            .build();
  }
}
