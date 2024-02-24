package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockBrownStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBrownStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_STAINED_GLASS)
            .build();
  }
}
