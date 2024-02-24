package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSeaLanternBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSeaLanternBehaviorInitializer {
  static void init() {
    BlockTypes.SEA_LANTERN_TYPE = BlockTypeBuilder
            .builder(BlockSeaLanternBehavior.class)
            .vanillaBlock(VanillaBlockId.SEA_LANTERN)
            .build();
  }
}
