package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemStoneBlockSlab3Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab3StackInitializer {
  static void init() {
    ItemTypes.STONE_BLOCK_SLAB3_TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlab3Stack.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB3)
            .build();
  }
}
