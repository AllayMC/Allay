package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockOrangeTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockOrangeTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_TERRACOTTA)
            .build();
  }
}
