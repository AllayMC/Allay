package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPodzolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPodzolBehaviorInitializer {
  static void init() {
    BlockTypes.PODZOL_TYPE = BlockTypeBuilder
            .builder(BlockPodzolBehavior.class)
            .vanillaBlock(VanillaBlockId.PODZOL)
            .build();
  }
}
