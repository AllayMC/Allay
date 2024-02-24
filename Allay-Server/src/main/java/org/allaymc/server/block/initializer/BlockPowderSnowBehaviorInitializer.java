package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPowderSnowBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPowderSnowBehaviorInitializer {
  static void init() {
    BlockTypes.POWDER_SNOW_TYPE = BlockTypeBuilder
            .builder(BlockPowderSnowBehavior.class)
            .vanillaBlock(VanillaBlockId.POWDER_SNOW)
            .build();
  }
}
