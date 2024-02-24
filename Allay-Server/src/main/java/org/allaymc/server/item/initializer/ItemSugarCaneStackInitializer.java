package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSugarCaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSugarCaneStackInitializer {
  static void init() {
    ItemTypes.SUGAR_CANE_TYPE = ItemTypeBuilder
            .builder(ItemSugarCaneStack.class)
            .vanillaItem(VanillaItemId.SUGAR_CANE)
            .build();
  }
}
