package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockNetheriteBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockNetheriteBlockBehaviorInitializer {
  static void init() {
    BlockTypes.NETHERITE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockNetheriteBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK)
            .build();
  }
}
