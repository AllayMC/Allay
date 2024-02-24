package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemCakeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCakeStackInitializer {
  static void init() {
    ItemTypes.ITEM_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemItemCakeStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAKE)
            .build();
  }
}
