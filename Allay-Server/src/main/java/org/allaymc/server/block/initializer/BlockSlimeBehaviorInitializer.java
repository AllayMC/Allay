package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSlimeBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSlimeBehaviorInitializer {
  static void init() {
    BlockTypes.SLIME_TYPE = BlockTypeBuilder
            .builder(BlockSlimeBehavior.class)
            .vanillaBlock(VanillaBlockId.SLIME)
            .build();
  }
}
