package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFurnaceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFurnaceStackInitializer {
  static void init() {
    ItemTypes.FURNACE_TYPE = ItemTypeBuilder
            .builder(ItemFurnaceStack.class)
            .vanillaItem(VanillaItemId.FURNACE)
            .build();
  }
}
