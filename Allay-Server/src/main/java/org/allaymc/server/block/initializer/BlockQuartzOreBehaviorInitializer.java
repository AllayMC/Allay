package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockQuartzOreBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockQuartzOreBehaviorInitializer {
  static void init() {
    BlockTypes.QUARTZ_ORE_TYPE = BlockTypeBuilder
            .builder(BlockQuartzOreBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_ORE)
            .build();
  }
}
