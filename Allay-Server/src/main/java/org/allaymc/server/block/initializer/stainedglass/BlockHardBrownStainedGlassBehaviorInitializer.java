package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardBrownStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBrownStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BROWN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBrownStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BROWN_STAINED_GLASS)
            .build();
  }
}
