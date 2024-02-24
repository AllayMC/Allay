package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockDioriteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDioriteBehaviorInitializer {
  static void init() {
    BlockTypes.DIORITE_TYPE = BlockTypeBuilder
            .builder(BlockDioriteBehavior.class)
            .vanillaBlock(VanillaBlockId.DIORITE)
            .build();
  }
}
