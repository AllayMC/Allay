package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockTuffBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTuffBricksBehaviorInitializer {
  static void init() {
    BlockTypes.TUFF_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockTuffBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF_BRICKS)
            .build();
  }
}
