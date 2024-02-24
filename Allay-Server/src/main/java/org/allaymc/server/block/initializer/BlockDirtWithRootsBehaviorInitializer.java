package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDirtWithRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDirtWithRootsBehaviorInitializer {
  static void init() {
    BlockTypes.DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockDirtWithRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS)
            .build();
  }
}
