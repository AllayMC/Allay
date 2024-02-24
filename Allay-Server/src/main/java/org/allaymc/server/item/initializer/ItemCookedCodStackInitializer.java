package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCookedCodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedCodStackInitializer {
  static void init() {
    ItemTypes.COOKED_COD_TYPE = ItemTypeBuilder
            .builder(ItemCookedCodStack.class)
            .vanillaItem(VanillaItemId.COOKED_COD)
            .build();
  }
}
