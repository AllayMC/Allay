package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockPurpleCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPurpleCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.PURPLE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockPurpleCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPLE_CARPET)
            .build();
  }
}
