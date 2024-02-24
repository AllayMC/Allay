package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockRawCopperBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRawCopperBlockBehaviorInitializer {
  static void init() {
    BlockTypes.RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRawCopperBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK)
            .build();
  }
}
