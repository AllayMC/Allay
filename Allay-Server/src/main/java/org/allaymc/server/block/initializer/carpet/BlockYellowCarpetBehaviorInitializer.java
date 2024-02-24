package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockYellowCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockYellowCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_CARPET)
            .build();
  }
}
