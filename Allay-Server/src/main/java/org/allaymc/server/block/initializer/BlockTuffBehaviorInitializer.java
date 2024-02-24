package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTuffBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTuffBehaviorInitializer {
  static void init() {
    BlockTypes.TUFF_TYPE = BlockTypeBuilder
            .builder(BlockTuffBehavior.class)
            .vanillaBlock(VanillaBlockId.TUFF)
            .build();
  }
}
