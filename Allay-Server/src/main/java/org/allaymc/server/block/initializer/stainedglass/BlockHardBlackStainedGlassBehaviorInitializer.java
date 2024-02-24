package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardBlackStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardBlackStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_BLACK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardBlackStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_BLACK_STAINED_GLASS)
            .build();
  }
}
