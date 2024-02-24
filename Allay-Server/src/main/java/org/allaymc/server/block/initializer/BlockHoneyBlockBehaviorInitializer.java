package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockHoneyBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockHoneyBlockBehaviorInitializer {
  static void init() {
    BlockTypes.HONEY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHoneyBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HONEY_BLOCK)
            .build();
  }
}
