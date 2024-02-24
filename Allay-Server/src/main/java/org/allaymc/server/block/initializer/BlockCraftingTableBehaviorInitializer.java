package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCraftingTableBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.server.block.component.craftingtable.BlockCraftingTableBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCraftingTableBehaviorInitializer {
  static void init() {
    BlockTypes.CRAFTING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockCraftingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
            .setBlockBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
            .build();
  }
}
