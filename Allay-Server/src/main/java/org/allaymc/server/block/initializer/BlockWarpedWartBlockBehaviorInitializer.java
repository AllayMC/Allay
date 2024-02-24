package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWarpedWartBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWarpedWartBlockBehaviorInitializer {
  static void init() {
    BlockTypes.WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockWarpedWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK)
            .build();
  }
}
