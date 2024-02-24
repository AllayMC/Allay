package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER)
            .build();
  }
}
