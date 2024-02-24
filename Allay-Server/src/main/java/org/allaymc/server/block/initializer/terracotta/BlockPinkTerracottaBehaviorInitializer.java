package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockPinkTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockPinkTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_TERRACOTTA)
            .build();
  }
}
