package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCakeStackInitializer {
  static void init() {
    ItemTypes.CAKE_TYPE = ItemTypeBuilder
            .builder(ItemCakeStack.class)
            .vanillaItem(VanillaItemId.CAKE)
            .build();
  }
}
