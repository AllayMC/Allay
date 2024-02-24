package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockClayBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockClayBehaviorInitializer {
  static void init() {
    BlockTypes.CLAY_TYPE = BlockTypeBuilder
            .builder(BlockClayBehavior.class)
            .vanillaBlock(VanillaBlockId.CLAY)
            .build();
  }
}
