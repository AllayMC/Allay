package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockBrownTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBrownTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_TERRACOTTA)
            .build();
  }
}
