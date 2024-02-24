package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDragonEggBehaviorInitializer {
  static void init() {
    BlockTypes.DRAGON_EGG_TYPE = BlockTypeBuilder
            .builder(BlockDragonEggBehavior.class)
            .vanillaBlock(VanillaBlockId.DRAGON_EGG)
            .build();
  }
}
