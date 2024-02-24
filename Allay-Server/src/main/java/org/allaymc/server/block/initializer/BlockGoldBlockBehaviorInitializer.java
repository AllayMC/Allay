package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGoldBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGoldBlockBehaviorInitializer {
  static void init() {
    BlockTypes.GOLD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockGoldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_BLOCK)
            .build();
  }
}
