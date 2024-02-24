package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockFletchingTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockFletchingTableBehaviorInitializer {
  static void init() {
    BlockTypes.FLETCHING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockFletchingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.FLETCHING_TABLE)
            .build();
  }
}
