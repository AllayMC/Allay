package org.allaymc.server.block.initializer.terracotta;

import org.allaymc.api.block.interfaces.terracotta.BlockYellowTerracottaBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowTerracottaBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockYellowTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_TERRACOTTA)
            .build();
  }
}
