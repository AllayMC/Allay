package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_OXIDIZED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER)
            .build();
  }
}
