package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockRedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.RED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockRedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_TERRACOTTA)
            .build();
  }
}
