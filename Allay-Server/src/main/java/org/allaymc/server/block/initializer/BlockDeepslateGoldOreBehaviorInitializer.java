package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateGoldOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateGoldOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
            .build();
  }
}
