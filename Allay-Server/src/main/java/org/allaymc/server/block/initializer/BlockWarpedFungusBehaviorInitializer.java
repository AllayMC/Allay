package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedFungusBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedFungusBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_FUNGUS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedFungusBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_FUNGUS)
            .build();
  }
}
