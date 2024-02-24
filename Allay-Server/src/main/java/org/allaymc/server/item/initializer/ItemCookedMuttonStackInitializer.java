package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedMuttonStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedMuttonStackInitializer {
  static void init() {
    ItemTypes.COOKED_MUTTON_TYPE = ItemTypeBuilder
            .builder(ItemCookedMuttonStack.class)
            .vanillaItem(VanillaItemId.COOKED_MUTTON)
            .build();
  }
}
