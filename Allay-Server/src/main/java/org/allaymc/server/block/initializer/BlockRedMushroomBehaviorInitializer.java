package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRedMushroomBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBehaviorInitializer {
  static void init() {
    BlockTypes.RED_MUSHROOM_TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM)
            .build();
  }
}
