package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockSprucePlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSprucePlanksBehaviorInitializer {
  static void init() {
    BlockTypes.SPRUCE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockSprucePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_PLANKS)
            .build();
  }
}
