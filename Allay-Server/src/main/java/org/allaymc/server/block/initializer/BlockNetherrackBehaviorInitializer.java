package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherrackBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherrackBehaviorInitializer {
  static void init() {
    BlockTypes.NETHERRACK_TYPE = BlockTypeBuilder
            .builder(BlockNetherrackBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERRACK)
            .build();
  }
}
