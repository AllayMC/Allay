package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockGreenStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockGreenStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_STAINED_GLASS)
            .build();
  }
}
