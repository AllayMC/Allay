package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockCherryFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryFenceBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockCherryFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_FENCE)
            .build();
  }
}
