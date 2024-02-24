package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRawGoldBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRawGoldBlockBehaviorInitializer {
  static void init() {
    BlockTypes.RAW_GOLD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawGoldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK)
            .build();
  }
}
