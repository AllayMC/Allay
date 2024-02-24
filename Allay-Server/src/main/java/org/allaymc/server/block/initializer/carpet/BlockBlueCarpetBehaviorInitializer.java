package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockBlueCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlueCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.BLUE_CARPET)
            .build();
  }
}
