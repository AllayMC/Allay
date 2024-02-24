package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockBambooFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooFenceBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockBambooFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_FENCE)
            .build();
  }
}
