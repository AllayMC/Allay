package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockBlackGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBlackGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
