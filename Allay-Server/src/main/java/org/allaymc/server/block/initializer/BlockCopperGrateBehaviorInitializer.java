package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperGrateBehaviorInitializer {
  static void init() {
    BlockTypes.COPPER_GRATE_TYPE = BlockTypeBuilder
            .builder(BlockCopperGrateBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_GRATE)
            .build();
  }
}
