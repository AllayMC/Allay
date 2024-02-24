package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStoneBehaviorInitializer {
  static void init() {
    BlockTypes.STONE_TYPE = BlockTypeBuilder
            .builder(BlockStoneBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE)
            .build();
  }
}
