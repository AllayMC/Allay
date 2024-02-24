package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCraftingTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCraftingTableStackInitializer {
  static void init() {
    ItemTypes.CRAFTING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemCraftingTableStack.class)
            .vanillaItem(VanillaItemId.CRAFTING_TABLE)
            .build();
  }
}
