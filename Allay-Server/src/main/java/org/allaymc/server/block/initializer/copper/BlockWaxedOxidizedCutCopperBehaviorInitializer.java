package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedCutCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCutCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
  }
}
