package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAllowBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAllowBehaviorInitializer {
  static void init() {
    BlockTypes.ALLOW_TYPE = BlockTypeBuilder
            .builder(BlockAllowBehavior.class)
            .vanillaBlock(VanillaBlockId.ALLOW)
            .build();
  }
}
