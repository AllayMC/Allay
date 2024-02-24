package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardLightBlueStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardLightBlueStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLightBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
            .build();
  }
}
