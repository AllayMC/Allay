package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEmeraldBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEmeraldBlockBehaviorInitializer {
  static void init() {
    BlockTypes.EMERALD_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockEmeraldBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.EMERALD_BLOCK)
            .build();
  }
}
