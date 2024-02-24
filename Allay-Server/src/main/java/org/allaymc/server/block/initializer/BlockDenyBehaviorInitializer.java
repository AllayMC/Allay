package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDenyBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDenyBehaviorInitializer {
  static void init() {
    BlockTypes.DENY_TYPE = BlockTypeBuilder
            .builder(BlockDenyBehavior.class)
            .vanillaBlock(VanillaBlockId.DENY)
            .build();
  }
}
