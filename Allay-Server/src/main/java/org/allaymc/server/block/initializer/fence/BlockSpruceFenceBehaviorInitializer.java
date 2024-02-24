package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockSpruceFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSpruceFenceBehaviorInitializer {
  static void init() {
    BlockTypes.SPRUCE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockSpruceFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_FENCE)
            .build();
  }
}
