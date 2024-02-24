package org.allaymc.server.block.initializer.glass;

import org.allaymc.api.block.interfaces.glass.BlockHardGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardGlassBehaviorInitializer {
  static void init() {
    BlockTypes.HARD_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS)
            .build();
  }
}
