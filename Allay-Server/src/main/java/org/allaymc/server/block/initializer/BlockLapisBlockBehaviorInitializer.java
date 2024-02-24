package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLapisBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLapisBlockBehaviorInitializer {
  static void init() {
    BlockTypes.LAPIS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLapisBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_BLOCK)
            .build();
  }
}
