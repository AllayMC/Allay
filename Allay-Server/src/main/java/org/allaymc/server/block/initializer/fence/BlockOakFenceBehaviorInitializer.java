package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockOakFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOakFenceBehaviorInitializer {
  static void init() {
    BlockTypes.OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_FENCE)
            .build();
  }
}
