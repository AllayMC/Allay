package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedNetherBrickBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedNetherBrickBehaviorInitializer {
  static void init() {
    BlockTypes.RED_NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockRedNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK)
            .build();
  }
}
