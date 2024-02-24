package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemWheatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWheatStackInitializer {
  static void init() {
    ItemTypes.ITEM_WHEAT_TYPE = ItemTypeBuilder
            .builder(ItemItemWheatStack.class)
            .vanillaItem(VanillaItemId.ITEM_WHEAT)
            .build();
  }
}
