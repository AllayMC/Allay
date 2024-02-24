package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWaterlilyBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWaterlilyBehaviorInitializer {
  static void init() {
    BlockTypes.WATERLILY_TYPE = BlockTypeBuilder
            .builder(BlockWaterlilyBehavior.class)
            .vanillaBlock(VanillaBlockId.WATERLILY)
            .build();
  }
}
