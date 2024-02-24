package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockBirchPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockBirchPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_PLANKS)
            .build();
  }
}
