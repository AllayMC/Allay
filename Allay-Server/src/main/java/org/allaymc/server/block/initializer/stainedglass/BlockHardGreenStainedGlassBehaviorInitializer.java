package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardGreenStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGreenStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_GREEN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGreenStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GREEN_STAINED_GLASS)
            .build();
  }
}
