package org.allaymc.server.block.initializer.glass;

import org.allaymc.api.block.interfaces.glass.BlockTintedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTintedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.TINTED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockTintedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.TINTED_GLASS)
            .build();
  }
}
