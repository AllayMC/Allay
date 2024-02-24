package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockOxidizedChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOxidizedChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.OXIDIZED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockOxidizedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.OXIDIZED_CHISELED_COPPER)
            .build();
  }
}
