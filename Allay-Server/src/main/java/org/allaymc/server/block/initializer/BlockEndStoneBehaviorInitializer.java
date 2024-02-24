package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEndStoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndStoneBehaviorInitializer {
  static void init() {
    BlockTypes.END_STONE_TYPE = BlockTypeBuilder
            .builder(BlockEndStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.END_STONE)
            .build();
  }
}
