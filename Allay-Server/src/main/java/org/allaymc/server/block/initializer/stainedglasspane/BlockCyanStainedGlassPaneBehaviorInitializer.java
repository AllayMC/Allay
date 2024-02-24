package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockCyanStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockCyanStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_STAINED_GLASS_PANE)
            .build();
  }
}
