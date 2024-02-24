package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockMangroveFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveFenceBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockMangroveFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_FENCE)
            .build();
  }
}
