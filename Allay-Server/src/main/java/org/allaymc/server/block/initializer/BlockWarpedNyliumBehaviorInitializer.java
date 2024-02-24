package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedNyliumBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedNyliumBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_NYLIUM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedNyliumBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_NYLIUM)
            .build();
  }
}
