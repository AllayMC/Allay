package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedSalmonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedSalmonStackInitializer {
  static void init() {
    ItemTypes.COOKED_SALMON_TYPE = ItemTypeBuilder
            .builder(ItemCookedSalmonStack.class)
            .vanillaItem(VanillaItemId.COOKED_SALMON)
            .build();
  }
}
