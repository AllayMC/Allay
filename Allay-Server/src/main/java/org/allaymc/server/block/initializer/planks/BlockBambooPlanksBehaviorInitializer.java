package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockBambooPlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooPlanksBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockBambooPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_PLANKS)
            .build();
  }
}
