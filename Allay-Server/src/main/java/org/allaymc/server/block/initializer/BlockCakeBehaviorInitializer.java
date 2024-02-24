package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCakeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCakeBehaviorInitializer {
  static void init() {
    BlockTypes.CAKE_TYPE = BlockTypeBuilder
            .builder(BlockCakeBehavior.class)
            .vanillaBlock(VanillaBlockId.CAKE)
            .setProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
            .build();
  }
}
