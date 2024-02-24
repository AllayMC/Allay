package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSmoothBasaltBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmoothBasaltBehaviorInitializer {
  static void init() {
    BlockTypes.SMOOTH_BASALT_TYPE = BlockTypeBuilder
            .builder(BlockSmoothBasaltBehavior.class)
            .vanillaBlock(VanillaBlockId.SMOOTH_BASALT)
            .build();
  }
}
