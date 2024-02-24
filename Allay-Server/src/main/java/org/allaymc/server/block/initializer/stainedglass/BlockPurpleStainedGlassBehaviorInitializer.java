package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockPurpleStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockPurpleStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_STAINED_GLASS)
            .build();
  }
}
