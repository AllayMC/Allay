package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockDarkOakFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakFenceBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE)
            .build();
  }
}
