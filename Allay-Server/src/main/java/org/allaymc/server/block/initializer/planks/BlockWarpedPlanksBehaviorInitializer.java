package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockWarpedPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_PLANKS)
            .build();
  }
}
