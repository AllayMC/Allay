package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockCyanCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCyanCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.CYAN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockCyanCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.CYAN_CARPET)
            .build();
  }
}
