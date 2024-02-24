package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherBrickBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK)
            .build();
  }
}
