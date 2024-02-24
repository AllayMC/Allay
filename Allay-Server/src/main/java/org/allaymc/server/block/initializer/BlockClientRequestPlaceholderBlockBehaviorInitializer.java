package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockClientRequestPlaceholderBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockClientRequestPlaceholderBlockBehaviorInitializer {
  static void init() {
    BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockClientRequestPlaceholderBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
            .build();
  }
}
