package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAirBehaviorInitializer {
  static void init() {
    BlockTypes.AIR_TYPE = BlockTypeBuilder
            .builder(BlockAirBehavior.class)
            .vanillaBlock(VanillaBlockId.AIR)
            .build();
  }
}
