package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAndesiteBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAndesiteBehaviorInitializer {
  static void init() {
    BlockTypes.ANDESITE_TYPE = BlockTypeBuilder
            .builder(BlockAndesiteBehavior.class)
            .vanillaBlock(VanillaBlockId.ANDESITE)
            .build();
  }
}
