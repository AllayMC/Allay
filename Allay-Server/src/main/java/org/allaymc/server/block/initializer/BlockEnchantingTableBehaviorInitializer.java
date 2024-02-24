package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEnchantingTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEnchantingTableBehaviorInitializer {
  static void init() {
    BlockTypes.ENCHANTING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockEnchantingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE)
            .build();
  }
}
