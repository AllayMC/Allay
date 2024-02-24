package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockGlowstoneBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockGlowstoneBehaviorInitializer {
  static void init() {
    BlockTypes.GLOWSTONE_TYPE = BlockTypeBuilder
            .builder(BlockGlowstoneBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOWSTONE)
            .build();
  }
}
