package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardPurpleStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardPurpleStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_PURPLE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardPurpleStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_PURPLE_STAINED_GLASS)
            .build();
  }
}
