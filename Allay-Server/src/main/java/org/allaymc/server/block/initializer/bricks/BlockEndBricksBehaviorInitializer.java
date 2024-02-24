package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockEndBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndBricksBehaviorInitializer {
  static void init() {
    BlockTypes.END_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockEndBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.END_BRICKS)
            .build();
  }
}
