package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPolishedTuffBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPolishedTuffBehaviorInitializer {
  static void init() {
    BlockTypes.POLISHED_TUFF_TYPE = BlockTypeBuilder
            .builder(BlockPolishedTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_TUFF)
            .build();
  }
}
