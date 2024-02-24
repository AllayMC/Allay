package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockDarkOakPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_PLANKS)
            .build();
  }
}
