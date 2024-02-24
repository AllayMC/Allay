package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockExposedCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.EXPOSED_COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER_GRATE)
            .build();
  }
}
