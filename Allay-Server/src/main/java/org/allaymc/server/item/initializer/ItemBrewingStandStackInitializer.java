package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrewingStandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrewingStandStackInitializer {
  static void init() {
    ItemTypes.BREWING_STAND_TYPE = ItemTypeBuilder
            .builder(ItemBrewingStandStack.class)
            .vanillaItem(VanillaItemId.BREWING_STAND)
            .build();
  }
}
