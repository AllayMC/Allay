package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedGraniteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedGraniteBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_GRANITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedGraniteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_GRANITE)
            .build();
  }
}
