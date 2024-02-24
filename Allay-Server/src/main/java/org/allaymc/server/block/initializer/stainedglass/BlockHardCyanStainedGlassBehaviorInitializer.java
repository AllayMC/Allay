package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardCyanStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardCyanStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_CYAN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardCyanStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_CYAN_STAINED_GLASS)
            .build();
  }
}
