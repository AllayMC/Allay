package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockCyanStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockCyanStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_STAINED_GLASS)
            .build();
  }
}
