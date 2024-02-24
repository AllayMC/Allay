package org.allaymc.server.block.initializer.glasspane;

import org.allaymc.api.block.interfaces.glasspane.BlockGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GLASS_PANE)
            .build();
  }
}
