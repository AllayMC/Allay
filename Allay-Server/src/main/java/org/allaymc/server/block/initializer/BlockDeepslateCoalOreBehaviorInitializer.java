package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDeepslateCoalOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDeepslateCoalOreBehaviorInitializer {
  static void init() {
    BlockTypes.DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCoalOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE)
            .build();
  }
}
