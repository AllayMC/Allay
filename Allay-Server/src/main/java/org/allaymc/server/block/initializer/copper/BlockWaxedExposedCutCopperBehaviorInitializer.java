package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedExposedCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER)
            .build();
  }
}
