package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardPinkStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardPinkStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardPinkStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PINK_STAINED_GLASS)
            .build();
  }
}
