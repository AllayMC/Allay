package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockOakPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOakPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.OAK_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockOakPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_PLANKS)
            .build();
  }
}
