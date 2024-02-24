package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemSkullStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSkullStackInitializer {
  static void init() {
    ItemTypes.ITEM_SKULL_TYPE = ItemTypeBuilder
            .builder(ItemItemSkullStack.class)
            .vanillaItem(VanillaItemId.ITEM_SKULL)
            .build();
  }
}
