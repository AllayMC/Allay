package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHardenedClayBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHardenedClayBehaviorInitializer {
  static void init() {
    BlockTypes.HARDENED_CLAY_TYPE = BlockTypeBuilder
            .builder(BlockHardenedClayBehavior.class)
            .vanillaBlock(VanillaBlockId.HARDENED_CLAY)
            .build();
  }
}
