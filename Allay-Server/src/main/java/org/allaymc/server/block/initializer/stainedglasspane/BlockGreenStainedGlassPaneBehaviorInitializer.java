package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockGreenStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockGreenStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_STAINED_GLASS_PANE)
            .build();
  }
}
