package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonNyliumBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonNyliumBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonNyliumBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM)
            .build();
  }
}
