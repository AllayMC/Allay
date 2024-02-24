package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAmethystBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAmethystBlockBehaviorInitializer {
  static void init() {
    BlockTypes.AMETHYST_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockAmethystBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.AMETHYST_BLOCK)
            .build();
  }
}
