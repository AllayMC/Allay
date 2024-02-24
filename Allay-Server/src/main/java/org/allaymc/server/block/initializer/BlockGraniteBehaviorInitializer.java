package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGraniteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGraniteBehaviorInitializer {
  static void init() {
    BlockTypes.GRANITE_TYPE = BlockTypeBuilder
            .builder(BlockGraniteBehavior.class)
            .vanillaBlock(VanillaBlockId.GRANITE)
            .build();
  }
}
