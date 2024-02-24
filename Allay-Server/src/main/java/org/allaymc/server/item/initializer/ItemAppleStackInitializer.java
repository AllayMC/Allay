package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAppleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAppleStackInitializer {
  static void init() {
    ItemTypes.APPLE_TYPE = ItemTypeBuilder
            .builder(ItemAppleStack.class)
            .vanillaItem(VanillaItemId.APPLE)
            .build();
  }
}
