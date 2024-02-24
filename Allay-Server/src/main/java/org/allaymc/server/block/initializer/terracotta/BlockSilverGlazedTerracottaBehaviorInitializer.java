package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockSilverGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSilverGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.SILVER_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockSilverGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
