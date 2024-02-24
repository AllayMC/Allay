package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockBrownCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBrownCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_CARPET)
            .build();
  }
}
