package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockLimeTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLimeTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_TERRACOTTA)
            .build();
  }
}
