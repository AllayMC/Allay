package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBrownMushroomBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrownMushroomBehaviorInitializer {
  static void init() {
    BlockTypes.BROWN_MUSHROOM_TYPE = BlockTypeBuilder
            .builder(BlockBrownMushroomBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM)
            .build();
  }
}
