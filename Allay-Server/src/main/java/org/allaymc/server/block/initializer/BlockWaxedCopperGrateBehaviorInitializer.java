package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaxedCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.WAXED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER_GRATE)
            .build();
  }
}
