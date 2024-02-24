package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTallgrassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTallgrassBehaviorInitializer {
  static void init() {
    BlockTypes.TALLGRASS_TYPE = BlockTypeBuilder
            .builder(BlockTallgrassBehavior.class)
            .vanillaBlock(VanillaBlockId.TALLGRASS)
            .setProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
            .build();
  }
}
