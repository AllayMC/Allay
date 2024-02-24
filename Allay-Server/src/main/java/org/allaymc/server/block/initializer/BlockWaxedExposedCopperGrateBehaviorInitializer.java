package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaxedExposedCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedExposedCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_EXPOSED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedExposedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER_GRATE)
            .build();
  }
}
