package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockHardLimeStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardLimeStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_LIME_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardLimeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_LIME_STAINED_GLASS)
            .build();
  }
}
