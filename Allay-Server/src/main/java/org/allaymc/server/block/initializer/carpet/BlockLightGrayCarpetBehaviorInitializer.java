package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockLightGrayCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightGrayCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightGrayCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET)
            .build();
  }
}
