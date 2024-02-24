package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGrassBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGrassBehaviorInitializer {
  static void init() {
    BlockTypes.GRASS_TYPE = BlockTypeBuilder
            .builder(BlockGrassBehavior.class)
            .vanillaBlock(VanillaBlockId.GRASS)
            .build();
  }
}
