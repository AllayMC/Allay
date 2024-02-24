package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedDioriteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedDioriteBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_DIORITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedDioriteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_DIORITE)
            .build();
  }
}
