package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CHISELED_COPPER)
            .build();
  }
}
