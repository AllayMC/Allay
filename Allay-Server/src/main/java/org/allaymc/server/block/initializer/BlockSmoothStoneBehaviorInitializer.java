package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSmoothStoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothStoneBehaviorInitializer {
  static void init() {
    BlockTypes.SMOOTH_STONE_TYPE = BlockTypeBuilder
            .builder(BlockSmoothStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_STONE)
            .build();
  }
}
