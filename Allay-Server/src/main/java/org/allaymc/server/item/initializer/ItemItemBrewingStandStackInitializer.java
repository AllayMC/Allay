package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemBrewingStandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBrewingStandStackInitializer {
  static void init() {
    ItemTypes.ITEM_BREWING_STAND_TYPE = ItemTypeBuilder
            .builder(ItemItemBrewingStandStack.class)
            .vanillaItem(VanillaItemId.ITEM_BREWING_STAND)
            .build();
  }
}
