package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedPorkchopStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedPorkchopStackInitializer {
  static void init() {
    ItemTypes.COOKED_PORKCHOP_TYPE = ItemTypeBuilder
            .builder(ItemCookedPorkchopStack.class)
            .vanillaItem(VanillaItemId.COOKED_PORKCHOP)
            .build();
  }
}
