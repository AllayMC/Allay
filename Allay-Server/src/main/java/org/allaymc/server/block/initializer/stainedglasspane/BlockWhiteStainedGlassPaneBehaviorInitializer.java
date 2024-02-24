package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockWhiteStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockWhiteStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_STAINED_GLASS_PANE)
            .build();
  }
}
