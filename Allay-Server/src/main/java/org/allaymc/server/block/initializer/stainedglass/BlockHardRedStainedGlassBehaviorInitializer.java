package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardRedStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardRedStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_RED_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardRedStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_RED_STAINED_GLASS)
            .build();
  }
}
