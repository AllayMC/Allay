package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockRedCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.RED_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockRedCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_CARPET)
            .build();
  }
}
