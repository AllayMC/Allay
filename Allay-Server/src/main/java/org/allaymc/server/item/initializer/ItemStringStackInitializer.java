package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStringStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStringStackInitializer {
  static void init() {
    ItemTypes.STRING_TYPE = ItemTypeBuilder
            .builder(ItemStringStack.class)
            .vanillaItem(VanillaItemId.STRING)
            .build();
  }
}
