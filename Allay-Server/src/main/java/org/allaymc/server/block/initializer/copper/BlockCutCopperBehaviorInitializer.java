package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.CUT_COPPER)
            .build();
  }
}
