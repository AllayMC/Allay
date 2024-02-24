package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockWhiteStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockWhiteStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_STAINED_GLASS)
            .build();
  }
}
