package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherGoldOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherGoldOreBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockNetherGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE)
            .build();
  }
}
