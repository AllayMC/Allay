package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockPurpleStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockPurpleStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_STAINED_GLASS_PANE)
            .build();
  }
}
