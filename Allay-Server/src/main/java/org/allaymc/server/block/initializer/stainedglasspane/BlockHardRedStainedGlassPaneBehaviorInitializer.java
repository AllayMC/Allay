package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardRedStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardRedStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_RED_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardRedStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_RED_STAINED_GLASS_PANE)
            .build();
  }
}
