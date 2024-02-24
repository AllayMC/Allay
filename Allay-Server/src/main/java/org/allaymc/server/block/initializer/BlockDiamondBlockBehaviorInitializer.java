package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDiamondBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDiamondBlockBehaviorInitializer {
  static void init() {
    BlockTypes.DIAMOND_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDiamondBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK)
            .build();
  }
}
