package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBrownStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBrownStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BROWN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBrownStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BROWN_STAINED_GLASS_PANE)
            .build();
  }
}
