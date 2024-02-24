package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedAndesiteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedAndesiteBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_ANDESITE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedAndesiteBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE)
            .build();
  }
}
