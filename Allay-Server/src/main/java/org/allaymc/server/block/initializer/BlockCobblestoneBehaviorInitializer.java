package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCobblestoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCobblestoneBehaviorInitializer {
  static void init() {
    BlockTypes.COBBLESTONE_TYPE = BlockTypeBuilder
            .builder(BlockCobblestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLESTONE)
            .build();
  }
}
