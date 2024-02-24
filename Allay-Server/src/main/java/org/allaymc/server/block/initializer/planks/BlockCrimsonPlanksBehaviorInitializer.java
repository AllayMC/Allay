package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockCrimsonPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS)
            .build();
  }
}
