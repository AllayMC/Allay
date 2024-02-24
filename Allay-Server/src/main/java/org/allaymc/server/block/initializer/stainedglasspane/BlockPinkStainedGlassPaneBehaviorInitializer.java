package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockPinkStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockPinkStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS_PANE)
            .build();
  }
}
