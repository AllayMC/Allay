package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockWhiteCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWhiteCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.WHITE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockWhiteCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.WHITE_CARPET)
            .build();
  }
}
