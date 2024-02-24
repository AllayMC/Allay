package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMovingBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMovingBlockBehaviorInitializer {
  static void init() {
    BlockTypes.MOVING_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMovingBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOVING_BLOCK)
            .build();
  }
}
