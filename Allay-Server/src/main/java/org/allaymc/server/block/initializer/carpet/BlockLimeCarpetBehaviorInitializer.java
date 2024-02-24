package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockLimeCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLimeCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.LIME_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLimeCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIME_CARPET)
            .build();
  }
}
