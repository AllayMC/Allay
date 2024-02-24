package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWeatheredCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER)
            .build();
  }
}
