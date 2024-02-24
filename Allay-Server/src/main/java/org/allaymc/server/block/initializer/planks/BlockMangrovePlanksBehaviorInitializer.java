package org.allaymc.server.block.initializer.planks;

import org.allaymc.api.block.interfaces.planks.BlockMangrovePlanksBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangrovePlanksBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockMangrovePlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS)
            .build();
  }
}
