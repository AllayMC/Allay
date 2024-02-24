package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardBlueStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBlueStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLUE_STAINED_GLASS)
            .build();
  }
}
