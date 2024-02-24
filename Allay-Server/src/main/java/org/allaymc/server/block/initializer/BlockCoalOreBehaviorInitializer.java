package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCoalOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoalOreBehaviorInitializer {
  static void init() {
    BlockTypes.COAL_ORE_TYPE = BlockTypeBuilder
            .builder(BlockCoalOreBehavior.class)
            .vanillaBlock(VanillaBlockId.COAL_ORE)
            .build();
  }
}
