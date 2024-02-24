package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockHardBlackStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBlackStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BLACK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockHardBlackStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLACK_STAINED_GLASS_PANE)
            .build();
  }
}
