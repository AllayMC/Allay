package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockLightBlueStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_STAINED_GLASS)
            .build();
  }
}
