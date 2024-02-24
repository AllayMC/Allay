package org.allaymc.server.block.initializer.bricks;

import org.allaymc.api.block.interfaces.bricks.BlockPolishedBlackstoneBricksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBricksBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstoneBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICKS)
            .build();
  }
}
