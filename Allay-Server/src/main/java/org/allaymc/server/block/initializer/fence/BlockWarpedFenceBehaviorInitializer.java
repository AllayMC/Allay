package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockWarpedFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedFenceBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockWarpedFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_FENCE)
            .build();
  }
}
