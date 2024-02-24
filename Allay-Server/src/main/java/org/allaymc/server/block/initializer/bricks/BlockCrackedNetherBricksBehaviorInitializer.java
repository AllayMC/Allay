package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockCrackedNetherBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrackedNetherBricksBehaviorInitializer {
  static void init() {
    BlockTypes.CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockCrackedNetherBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS)
            .build();
  }
}
