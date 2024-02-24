package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockCrimsonFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFenceBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FENCE)
            .build();
  }
}
