package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockBirchFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchFenceBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockBirchFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_FENCE)
            .build();
  }
}
