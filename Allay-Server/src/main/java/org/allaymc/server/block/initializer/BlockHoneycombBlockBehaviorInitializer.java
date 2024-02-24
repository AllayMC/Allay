package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHoneycombBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHoneycombBlockBehaviorInitializer {
  static void init() {
    BlockTypes.HONEYCOMB_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHoneycombBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HONEYCOMB_BLOCK)
            .build();
  }
}
