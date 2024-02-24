package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleStoneBlockSlab4Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab4StackInitializer {
  static void init() {
    ItemTypes.DOUBLE_STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab4Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB4)
            .build();
  }
}
