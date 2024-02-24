package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWheatBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWheatBehaviorInitializer {
  static void init() {
    BlockTypes.WHEAT_TYPE = BlockTypeBuilder
            .builder(BlockWheatBehavior.class)
            .vanillaBlock(VanillaBlockId.WHEAT)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
