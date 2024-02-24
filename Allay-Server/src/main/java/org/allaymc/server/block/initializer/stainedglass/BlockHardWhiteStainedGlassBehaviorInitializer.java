package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardWhiteStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardWhiteStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_WHITE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardWhiteStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_WHITE_STAINED_GLASS)
            .build();
  }
}
