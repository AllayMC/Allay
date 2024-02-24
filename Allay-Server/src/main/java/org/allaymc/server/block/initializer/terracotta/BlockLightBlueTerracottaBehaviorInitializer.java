package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockLightBlueTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_TERRACOTTA)
            .build();
  }
}
