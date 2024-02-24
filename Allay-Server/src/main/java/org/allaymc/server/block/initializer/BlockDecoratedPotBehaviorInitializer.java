package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDecoratedPotBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDecoratedPotBehaviorInitializer {
  static void init() {
    BlockTypes.DECORATED_POT_TYPE = BlockTypeBuilder
            .builder(BlockDecoratedPotBehavior.class)
            .vanillaBlock(VanillaBlockId.DECORATED_POT)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION)
            .build();
  }
}
