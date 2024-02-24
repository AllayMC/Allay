package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDripstoneBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDripstoneBlockBehaviorInitializer {
  static void init() {
    BlockTypes.DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockDripstoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK)
            .build();
  }
}
