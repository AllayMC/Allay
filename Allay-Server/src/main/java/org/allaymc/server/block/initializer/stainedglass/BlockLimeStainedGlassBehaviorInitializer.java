package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockLimeStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockLimeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_STAINED_GLASS)
            .build();
  }
}
