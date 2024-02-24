package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGoldOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGoldOreBehaviorInitializer {
  static void init() {
    BlockTypes.GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_ORE)
            .build();
  }
}
