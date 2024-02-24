package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardPurpleStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardPurpleStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardPurpleStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PURPLE_STAINED_GLASS_PANE)
            .build();
  }
}
