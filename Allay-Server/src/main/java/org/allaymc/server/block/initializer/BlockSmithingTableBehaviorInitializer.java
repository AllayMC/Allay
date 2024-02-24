package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSmithingTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSmithingTableBehaviorInitializer {
  static void init() {
    BlockTypes.SMITHING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockSmithingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.SMITHING_TABLE)
            .build();
  }
}
