package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockPurpleTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPurpleTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_TERRACOTTA)
            .build();
  }
}
