package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedRootsBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_ROOTS)
            .build();
  }
}
