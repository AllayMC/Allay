package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockRedStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.RED_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockRedStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_STAINED_GLASS)
            .build();
  }
}
