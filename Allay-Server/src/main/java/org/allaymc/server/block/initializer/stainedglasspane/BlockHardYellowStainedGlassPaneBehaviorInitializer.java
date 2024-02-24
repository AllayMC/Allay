package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardYellowStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardYellowStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardYellowStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_YELLOW_STAINED_GLASS_PANE)
            .build();
  }
}
