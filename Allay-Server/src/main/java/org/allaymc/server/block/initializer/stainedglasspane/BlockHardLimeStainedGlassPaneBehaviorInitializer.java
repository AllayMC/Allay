package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardLimeStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardLimeStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_LIME_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardLimeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIME_STAINED_GLASS_PANE)
            .build();
  }
}
