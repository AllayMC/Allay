package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedBeefStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedBeefStackInitializer {
  static void init() {
    ItemTypes.COOKED_BEEF_TYPE = ItemTypeBuilder
            .builder(ItemCookedBeefStack.class)
            .vanillaItem(VanillaItemId.COOKED_BEEF)
            .build();
  }
}
