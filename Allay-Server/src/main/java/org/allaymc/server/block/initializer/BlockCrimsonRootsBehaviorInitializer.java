package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonRootsBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_ROOTS)
            .build();
  }
}
