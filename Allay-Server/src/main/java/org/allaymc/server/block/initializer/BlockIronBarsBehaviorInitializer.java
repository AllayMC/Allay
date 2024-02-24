package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockIronBarsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIronBarsBehaviorInitializer {
  static void init() {
    BlockTypes.IRON_BARS_TYPE = BlockTypeBuilder
            .builder(BlockIronBarsBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_BARS)
            .build();
  }
}
