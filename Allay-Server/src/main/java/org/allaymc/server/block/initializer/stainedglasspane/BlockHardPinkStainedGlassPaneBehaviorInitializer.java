package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardPinkStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardPinkStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_PINK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardPinkStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PINK_STAINED_GLASS_PANE)
            .build();
  }
}
