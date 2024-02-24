package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMyceliumBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMyceliumBehaviorInitializer {
  static void init() {
    BlockTypes.MYCELIUM_TYPE = BlockTypeBuilder
            .builder(BlockMyceliumBehavior.class)
            .vanillaBlock(VanillaBlockId.MYCELIUM)
            .build();
  }
}
