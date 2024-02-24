package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockMudBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMudBricksBehaviorInitializer {
  static void init() {
    BlockTypes.MUD_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockMudBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.MUD_BRICKS)
            .build();
  }
}
