package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMonsterEggBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMonsterEggBehaviorInitializer {
  static void init() {
    BlockTypes.MONSTER_EGG_TYPE = BlockTypeBuilder
            .builder(BlockMonsterEggBehavior.class)
            .vanillaBlock(VanillaBlockId.MONSTER_EGG)
            .setProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
            .build();
  }
}
