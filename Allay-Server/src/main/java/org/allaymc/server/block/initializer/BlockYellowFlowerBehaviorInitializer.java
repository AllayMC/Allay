package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockYellowFlowerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockYellowFlowerBehaviorInitializer {
  static void init() {
    BlockTypes.YELLOW_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockYellowFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_FLOWER)
            .build();
  }
}
