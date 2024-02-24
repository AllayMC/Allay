package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockMagentaGlazedTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaGlazedTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockMagentaGlazedTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
