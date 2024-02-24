package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockChiseledNetherBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledNetherBricksBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockChiseledNetherBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS)
            .build();
  }
}
