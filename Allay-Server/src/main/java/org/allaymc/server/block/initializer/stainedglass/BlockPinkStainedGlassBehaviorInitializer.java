package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockPinkStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockPinkStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_STAINED_GLASS)
            .build();
  }
}
