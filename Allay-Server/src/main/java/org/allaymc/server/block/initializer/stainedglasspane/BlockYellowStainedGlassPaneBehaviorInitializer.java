package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockYellowStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockYellowStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_STAINED_GLASS_PANE)
            .build();
  }
}
