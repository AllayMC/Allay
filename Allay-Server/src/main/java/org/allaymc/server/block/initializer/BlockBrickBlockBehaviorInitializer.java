package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBrickBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBrickBlockBehaviorInitializer {
  static void init() {
    BlockTypes.BRICK_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBrickBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BRICK_BLOCK)
            .build();
  }
}
