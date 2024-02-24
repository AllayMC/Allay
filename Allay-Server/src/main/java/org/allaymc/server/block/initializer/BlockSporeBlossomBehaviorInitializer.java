package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSporeBlossomBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSporeBlossomBehaviorInitializer {
  static void init() {
    BlockTypes.SPORE_BLOSSOM_TYPE = BlockTypeBuilder
            .builder(BlockSporeBlossomBehavior.class)
            .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM)
            .build();
  }
}
