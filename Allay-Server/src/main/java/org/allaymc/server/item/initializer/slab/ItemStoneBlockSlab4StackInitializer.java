package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemStoneBlockSlab4Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab4StackInitializer {
  static void init() {
    ItemTypes.STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlab4Stack.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB4)
            .build();
  }
}
