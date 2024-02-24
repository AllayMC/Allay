package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockOrangeCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOrangeCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.ORANGE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockOrangeCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_CARPET)
            .build();
  }
}
