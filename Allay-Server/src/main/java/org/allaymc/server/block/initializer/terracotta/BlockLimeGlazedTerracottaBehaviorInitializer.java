package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockLimeGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLimeGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
