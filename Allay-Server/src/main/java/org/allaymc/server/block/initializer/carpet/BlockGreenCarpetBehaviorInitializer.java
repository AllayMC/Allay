package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockGreenCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGreenCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.GREEN_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockGreenCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.GREEN_CARPET)
            .build();
  }
}
