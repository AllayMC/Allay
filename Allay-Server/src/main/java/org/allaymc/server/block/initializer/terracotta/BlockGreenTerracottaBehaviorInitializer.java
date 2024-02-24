package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockGreenTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGreenTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_TERRACOTTA)
            .build();
  }
}
