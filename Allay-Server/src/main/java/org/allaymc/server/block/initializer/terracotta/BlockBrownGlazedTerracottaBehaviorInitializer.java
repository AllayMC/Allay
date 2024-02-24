package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockBrownGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockBrownGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
