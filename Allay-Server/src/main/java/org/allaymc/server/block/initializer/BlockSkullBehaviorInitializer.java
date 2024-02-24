package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSkullBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSkullBehaviorInitializer {
  static void init() {
    BlockTypes.SKULL_TYPE = BlockTypeBuilder
            .builder(BlockSkullBehavior.class)
            .vanillaBlock(VanillaBlockId.SKULL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
