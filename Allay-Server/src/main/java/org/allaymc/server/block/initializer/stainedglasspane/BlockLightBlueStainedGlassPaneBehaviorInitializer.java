package org.allaymc.server.block.initializer.stainedglasspane;

import org.allaymc.api.block.interfaces.stainedglasspane.BlockLightBlueStainedGlassPaneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueStainedGlassPaneBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueStainedGlassPaneBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
            .build();
  }
}
