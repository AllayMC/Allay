package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockLimeStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLimeStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_STAINED_GLASS_PANE)
            .build();
  }
}
