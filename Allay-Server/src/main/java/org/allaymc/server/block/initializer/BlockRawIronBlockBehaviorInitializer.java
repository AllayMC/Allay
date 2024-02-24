package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRawIronBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRawIronBlockBehaviorInitializer {
  static void init() {
    BlockTypes.RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawIronBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK)
            .build();
  }
}
