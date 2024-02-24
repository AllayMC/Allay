package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockConduitBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockConduitBehaviorInitializer {
  static void init() {
    BlockTypes.CONDUIT_TYPE = BlockTypeBuilder
            .builder(BlockConduitBehavior.class)
            .vanillaBlock(VanillaBlockId.CONDUIT)
            .build();
  }
}
