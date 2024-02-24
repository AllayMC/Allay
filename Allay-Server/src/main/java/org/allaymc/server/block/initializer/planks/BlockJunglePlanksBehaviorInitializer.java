package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockJunglePlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockJunglePlanksBehaviorInitializer {
  static void init() {
    BlockTypes.JUNGLE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockJunglePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_PLANKS)
            .build();
  }
}
