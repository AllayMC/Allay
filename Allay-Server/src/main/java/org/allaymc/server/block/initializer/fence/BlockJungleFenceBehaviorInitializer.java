package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockJungleFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJungleFenceBehaviorInitializer {
  static void init() {
    BlockTypes.JUNGLE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockJungleFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_FENCE)
            .build();
  }
}
