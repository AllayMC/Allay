package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWaxedOxidizedChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CHISELED_COPPER)
            .build();
  }
}
