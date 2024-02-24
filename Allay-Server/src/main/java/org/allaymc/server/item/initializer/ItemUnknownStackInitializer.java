package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemUnknownStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnknownStackInitializer {
  static void init() {
    ItemTypes.UNKNOWN_TYPE = ItemTypeBuilder
            .builder(ItemUnknownStack.class)
            .vanillaItem(VanillaItemId.UNKNOWN)
            .build();
  }
}
