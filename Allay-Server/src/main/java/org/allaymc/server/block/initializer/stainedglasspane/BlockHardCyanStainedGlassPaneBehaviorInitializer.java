package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardCyanStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardCyanStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_CYAN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardCyanStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_CYAN_STAINED_GLASS_PANE)
            .build();
  }
}
