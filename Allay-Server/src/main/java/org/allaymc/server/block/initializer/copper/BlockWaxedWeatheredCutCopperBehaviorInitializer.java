package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedWeatheredCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER)
            .build();
  }
}
