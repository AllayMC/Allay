package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockBlueStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBlueStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_STAINED_GLASS)
            .build();
  }
}
