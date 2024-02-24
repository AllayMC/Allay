package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockExposedCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER)
            .build();
  }
}
