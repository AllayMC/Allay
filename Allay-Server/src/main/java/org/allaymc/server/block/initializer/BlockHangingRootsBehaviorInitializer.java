package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHangingRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHangingRootsBehaviorInitializer {
  static void init() {
    BlockTypes.HANGING_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockHangingRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.HANGING_ROOTS)
            .build();
  }
}
