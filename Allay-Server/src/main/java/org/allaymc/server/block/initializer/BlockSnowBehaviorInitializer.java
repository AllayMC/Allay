package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSnowBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSnowBehaviorInitializer {
  static void init() {
    BlockTypes.SNOW_TYPE = BlockTypeBuilder
            .builder(BlockSnowBehavior.class)
            .vanillaBlock(VanillaBlockId.SNOW)
            .build();
  }
}
