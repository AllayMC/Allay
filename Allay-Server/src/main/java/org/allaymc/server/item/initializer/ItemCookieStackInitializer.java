package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookieStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookieStackInitializer {
  static void init() {
    ItemTypes.COOKIE_TYPE = ItemTypeBuilder
            .builder(ItemCookieStack.class)
            .vanillaItem(VanillaItemId.COOKIE)
            .build();
  }
}
