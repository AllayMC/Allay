package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER)
            .build();
  }
}
