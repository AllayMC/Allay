package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWeatheredChiseledCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredChiseledCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_CHISELED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredChiseledCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_CHISELED_COPPER)
            .build();
  }
}
