package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockBlackCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlackCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.BLACK_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBlackCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_CARPET)
            .build();
  }
}
