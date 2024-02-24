package org.allaymc.server.block.initializer.fence;

import org.allaymc.api.block.interfaces.fence.BlockAcaciaFenceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaFenceBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE)
            .build();
  }
}
