package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockRedStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.RED_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS_PANE)
            .build();
  }
}
