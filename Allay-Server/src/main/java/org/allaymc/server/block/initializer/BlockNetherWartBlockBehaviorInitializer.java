package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetherWartBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetherWartBlockBehaviorInitializer {
  static void init() {
    BlockTypes.NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNetherWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK)
            .build();
  }
}
