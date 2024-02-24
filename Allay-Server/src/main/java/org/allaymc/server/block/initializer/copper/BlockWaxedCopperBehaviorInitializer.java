package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER)
            .build();
  }
}
