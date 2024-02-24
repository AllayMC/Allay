package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockGrayCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrayCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.GRAY_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockGrayCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.GRAY_CARPET)
            .build();
  }
}
