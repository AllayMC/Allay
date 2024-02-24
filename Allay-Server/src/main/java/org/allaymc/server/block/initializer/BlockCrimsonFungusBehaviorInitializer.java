package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrimsonFungusBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFungusBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFungusBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS)
            .build();
  }
}
