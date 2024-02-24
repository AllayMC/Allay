package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMossyCobblestoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMossyCobblestoneBehaviorInitializer {
  static void init() {
    BlockTypes.MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
            .builder(BlockMossyCobblestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE)
            .build();
  }
}
