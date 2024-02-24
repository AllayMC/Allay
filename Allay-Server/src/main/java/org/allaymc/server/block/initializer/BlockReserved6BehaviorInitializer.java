package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockReserved6Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockReserved6BehaviorInitializer {
  static void init() {
    BlockTypes.RESERVED6_TYPE = BlockTypeBuilder
            .builder(BlockReserved6Behavior.class)
            .vanillaBlock(VanillaBlockId.RESERVED6)
            .build();
  }
}
