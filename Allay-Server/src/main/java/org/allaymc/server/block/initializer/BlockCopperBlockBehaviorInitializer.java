package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCopperBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCopperBlockBehaviorInitializer {
  static void init() {
    BlockTypes.COPPER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockCopperBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.COPPER_BLOCK)
            .build();
  }
}
