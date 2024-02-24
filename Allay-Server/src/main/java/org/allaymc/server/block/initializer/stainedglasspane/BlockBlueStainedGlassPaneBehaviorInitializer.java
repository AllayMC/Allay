package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_STAINED_GLASS_PANE)
            .build();
  }
}
