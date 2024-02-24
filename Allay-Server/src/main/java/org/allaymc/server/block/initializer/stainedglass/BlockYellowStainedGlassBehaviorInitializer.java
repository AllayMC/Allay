package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockYellowStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockYellowStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_STAINED_GLASS)
            .build();
  }
}
