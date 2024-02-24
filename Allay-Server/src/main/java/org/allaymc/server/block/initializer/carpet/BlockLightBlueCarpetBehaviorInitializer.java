package org.allaymc.server.block.initializer.carpet;

import org.allaymc.api.block.interfaces.carpet.BlockLightBlueCarpetBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightBlueCarpetBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
            .builder(BlockLightBlueCarpetBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET)
            .build();
  }
}
