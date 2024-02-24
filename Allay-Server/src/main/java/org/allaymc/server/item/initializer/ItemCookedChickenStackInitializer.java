package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedChickenStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedChickenStackInitializer {
  static void init() {
    ItemTypes.COOKED_CHICKEN_TYPE = ItemTypeBuilder
            .builder(ItemCookedChickenStack.class)
            .vanillaItem(VanillaItemId.COOKED_CHICKEN)
            .build();
  }
}
