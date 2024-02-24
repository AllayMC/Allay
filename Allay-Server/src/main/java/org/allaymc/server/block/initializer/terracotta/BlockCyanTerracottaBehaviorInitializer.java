package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockCyanTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockCyanTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_TERRACOTTA)
            .build();
  }
}
