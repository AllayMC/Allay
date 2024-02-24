package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockQuartzBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockQuartzBricksBehaviorInitializer {
  static void init() {
    BlockTypes.QUARTZ_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockQuartzBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS)
            .build();
  }
}
