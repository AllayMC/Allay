package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockUnknownBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUnknownBehaviorInitializer {
  static void init() {
    BlockTypes.UNKNOWN_TYPE = BlockTypeBuilder
            .builder(BlockUnknownBehavior.class)
            .vanillaBlock(VanillaBlockId.UNKNOWN)
            .build();
  }
}
