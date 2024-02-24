package org.allaymc.server.block.initializer.copper;

import org.allaymc.api.block.interfaces.copper.BlockWeatheredCopperBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWeatheredCopperBehaviorInitializer {
  static void init() {
    BlockTypes.WEATHERED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWeatheredCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WEATHERED_COPPER)
            .build();
  }
}
