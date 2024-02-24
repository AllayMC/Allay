package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaxedWeatheredCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_WEATHERED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER_GRATE)
            .build();
  }
}
