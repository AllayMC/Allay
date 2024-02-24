package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CHISELED_COPPER)
            .build();
  }
}
