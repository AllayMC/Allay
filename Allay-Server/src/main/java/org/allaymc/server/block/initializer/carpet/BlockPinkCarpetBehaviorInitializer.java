package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockPinkCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockPinkCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_CARPET)
            .build();
  }
}
