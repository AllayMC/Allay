package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockIronBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockIronBlockBehaviorInitializer {
  static void init() {
    BlockTypes.IRON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockIronBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.IRON_BLOCK)
            .build();
  }
}
