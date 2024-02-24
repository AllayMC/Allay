package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedExposedChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_EXPOSED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CHISELED_COPPER)
            .build();
  }
}
