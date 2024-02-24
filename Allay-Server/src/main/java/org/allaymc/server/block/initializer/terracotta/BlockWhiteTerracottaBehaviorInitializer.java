package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockWhiteTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockWhiteTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_TERRACOTTA)
            .build();
  }
}
