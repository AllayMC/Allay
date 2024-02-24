package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGrassPathBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrassPathBehaviorInitializer {
  static void init() {
    BlockTypes.GRASS_PATH_TYPE = BlockTypeBuilder
            .builder(BlockGrassPathBehavior.class)
            .vanillaBlock(VanillaBlockId.GRASS_PATH)
            .build();
  }
}
