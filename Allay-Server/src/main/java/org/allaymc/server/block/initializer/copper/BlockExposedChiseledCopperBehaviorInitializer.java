package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockExposedChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_CHISELED_COPPER)
            .build();
  }
}
