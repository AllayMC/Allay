package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockGrayTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockGrayTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_TERRACOTTA)
            .build();
  }
}
