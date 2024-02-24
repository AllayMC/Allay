package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockNetherBrickFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickFenceBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE)
            .build();
  }
}
