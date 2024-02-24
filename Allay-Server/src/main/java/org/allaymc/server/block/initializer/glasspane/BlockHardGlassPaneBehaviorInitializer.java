package org.allaymc.server.block.initializer.glasspane;

import org.allaymc.api.block.interfaces.glasspane.BlockHardGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE)
            .build();
  }
}
