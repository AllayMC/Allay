package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockLightBlueGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
