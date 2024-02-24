package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBlueStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLUE_STAINED_GLASS_PANE)
            .build();
  }
}
