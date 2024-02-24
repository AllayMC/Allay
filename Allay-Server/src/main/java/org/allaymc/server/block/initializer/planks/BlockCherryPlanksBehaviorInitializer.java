package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockCherryPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockCherryPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_PLANKS)
            .build();
  }
}
