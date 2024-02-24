package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockBlackStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockBlackStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_STAINED_GLASS_PANE)
            .build();
  }
}
