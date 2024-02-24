package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCoalBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCoalBlockBehaviorInitializer {
  static void init() {
    BlockTypes.COAL_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCoalBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COAL_BLOCK)
            .build();
  }
}
