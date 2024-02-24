package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockLightGrayTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_TERRACOTTA)
            .build();
  }
}
