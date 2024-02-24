package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMobSpawnerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMobSpawnerBehaviorInitializer {
  static void init() {
    BlockTypes.MOB_SPAWNER_TYPE = BlockTypeBuilder
            .builder(BlockMobSpawnerBehavior.class)
            .vanillaBlock(VanillaBlockId.MOB_SPAWNER)
            .build();
  }
}
