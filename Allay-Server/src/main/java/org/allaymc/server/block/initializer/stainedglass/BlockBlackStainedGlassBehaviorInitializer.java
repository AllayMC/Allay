package org.allaymc.server.block.initializer.stainedglass;

import org.allaymc.api.block.interfaces.stainedglass.BlockBlackStainedGlassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackStainedGlassBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBlackStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_STAINED_GLASS)
            .build();
  }
}
