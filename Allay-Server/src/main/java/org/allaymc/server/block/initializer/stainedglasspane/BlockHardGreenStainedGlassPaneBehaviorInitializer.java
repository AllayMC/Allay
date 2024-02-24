package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardGreenStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGreenStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_GREEN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGreenStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GREEN_STAINED_GLASS_PANE)
            .build();
  }
}
