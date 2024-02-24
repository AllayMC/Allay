package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDiamondOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDiamondOreBehaviorInitializer {
  static void init() {
    BlockTypes.DIAMOND_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDiamondOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DIAMOND_ORE)
            .build();
  }
}
