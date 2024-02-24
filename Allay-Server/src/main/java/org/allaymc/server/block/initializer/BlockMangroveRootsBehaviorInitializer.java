package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMangroveRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveRootsBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockMangroveRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS)
            .build();
  }
}
