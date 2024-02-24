package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardWhiteStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardWhiteStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_WHITE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardWhiteStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_WHITE_STAINED_GLASS_PANE)
            .build();
  }
}
