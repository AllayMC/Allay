package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockBlueTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlueTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_TERRACOTTA)
            .build();
  }
}
