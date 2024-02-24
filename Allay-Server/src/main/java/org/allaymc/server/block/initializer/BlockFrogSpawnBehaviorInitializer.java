package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFrogSpawnBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFrogSpawnBehaviorInitializer {
  static void init() {
    BlockTypes.FROG_SPAWN_TYPE = BlockTypeBuilder
            .builder(BlockFrogSpawnBehavior.class)
            .vanillaBlock(VanillaBlockId.FROG_SPAWN)
            .build();
  }
}
