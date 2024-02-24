package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSoulSandBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulSandBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_SAND_TYPE = BlockTypeBuilder
            .builder(BlockSoulSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_SAND)
            .build();
  }
}
