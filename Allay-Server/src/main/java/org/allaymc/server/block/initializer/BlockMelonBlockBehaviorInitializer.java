package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMelonBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMelonBlockBehaviorInitializer {
  static void init() {
    BlockTypes.MELON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMelonBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MELON_BLOCK)
            .build();
  }
}
